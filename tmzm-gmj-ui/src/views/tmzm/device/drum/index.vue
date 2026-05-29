<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="转鼓号" prop="rotatingDrumNo">
        <el-input v-model="queryParams.rotatingDrumNo" placeholder="请输入转鼓号" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="穿孔辊号" prop="rollerNoTr">
        <el-input v-model="queryParams.rollerNoTr" placeholder="请输入穿孔辊号" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="设备状态" prop="deviceStatus">
        <el-select v-model="queryParams.deviceStatus" placeholder="请选择设备状态" clearable style="width: 200px">
          <el-option label="离线" value="0" />
          <el-option label="在线" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleScrap">报废</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Top" :disabled="multiple" @click="handleOnline">上线</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Bottom" :disabled="multiple" @click="handleOffline">下线</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="drumList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="转鼓号" align="center" prop="rotatingDrumNo" width="120" />
      <el-table-column label="穿孔辊号" align="center" prop="rollerNoTr" width="120" />
      <el-table-column label="装配编号" align="center" prop="asmatNo" min-width="120" />
      <el-table-column label="装配名称" align="center" prop="asmatName" min-width="150" />
      <el-table-column label="设备状态" align="center" prop="deviceStatus" width="90">
        <template #default="scope">
          <el-tag :type="scope.row.deviceStatus === '1' ? 'success' : 'danger'">
            {{ scope.row.deviceStatus === '1' ? '在线' : '离线' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="去向工位" align="center" prop="destinationStation" width="100">
        <template #default="scope">
          <el-tag :type="getStationTagType(scope.row.destinationStation)">
            {{ getStationLabel(scope.row.destinationStation) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="公称直径" align="center" prop="nominalDia" width="100" />
      <el-table-column label="小轴承" align="center" prop="bearingSmall" width="100" />
      <el-table-column label="大轴承" align="center" prop="bearingBig" width="100" />
      <el-table-column label="操作" align="center" width="180" fixed="right">
        <template #default="scope">
          <el-button type="text" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="Delete" @click="handleScrap(scope.row)">报废</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      v-show="total > 0"
      :total="total"
      v-model:current-page="queryParams.pageNum"
      v-model:page-size="queryParams.pageSize"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="getList"
      @current-change="getList"
    />

    <!-- 新增/修改对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="700px" append-to-body>
      <el-tabs v-model="dialogTab">
        <el-tab-pane label="主信息" name="main">
          <el-form ref="drumRef" :model="form" :rules="rules" label-width="100px">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="转鼓号" prop="rotatingDrumNo">
                  <el-input v-model="form.rotatingDrumNo" placeholder="请输入转鼓号" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="穿孔辊号" prop="rollerNoTr">
                  <el-input v-model="form.rollerNoTr" placeholder="请输入穿孔辊号" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="装配编号" prop="asmatNo">
                  <el-input v-model="form.asmatNo" placeholder="请输入装配编号" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="装配名称" prop="asmatName">
                  <el-input v-model="form.asmatName" placeholder="请输入装配名称" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="设备状态" prop="deviceStatus">
                  <el-select v-model="form.deviceStatus" placeholder="请选择设备状态" style="width: 100%">
                    <el-option label="离线" value="0" />
                    <el-option label="在线" value="1" />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="去向工位" prop="destinationStation">
                  <el-select v-model="form.destinationStation" placeholder="请选择去向工位" style="width: 100%">
                    <el-option label="备用" value="01" />
                    <el-option label="装配" value="02" />
                    <el-option label="加工" value="03" />
                    <el-option label="报废" value="04" />
                    <el-option label="拆辊" value="05" />
                    <el-option label="装辊" value="06" />
                    <el-option label="检查" value="07" />
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="公称直径" prop="nominalDia">
                  <el-input v-model="form.nominalDia" placeholder="请输入公称直径" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="12">
                <el-form-item label="小轴承" prop="bearingSmall">
                  <el-input v-model="form.bearingSmall" placeholder="请输入小轴承" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="大轴承" prop="bearingBig">
                  <el-input v-model="form.bearingBig" placeholder="请输入大轴承" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="组成信息" name="components">
          <el-table :data="componentList" stripe border>
            <el-table-column label="序号" align="center" type="index" width="60" />
            <el-table-column label="零件编号" align="center" prop="partNo" min-width="120" />
            <el-table-column label="零件名称" align="center" prop="partName" min-width="120" />
            <el-table-column label="数量" align="center" prop="quantity" width="80" />
            <el-table-column label="规格" align="center" prop="spec" width="100" />
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { listDrum, getDrum, insertDrum, updateDrum, scrapDrum, onlineDrum, offlineDrum } from '@/api/tmzm/device/drum'
import { ElMessage, ElMessageBox } from 'element-plus'

const drumList = ref([])
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const componentList = ref([])
const dialogTab = ref('main')

const queryRef = ref(null)
const drumRef = ref(null)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  rotatingDrumNo: undefined,
  rollerNoTr: undefined,
  deviceStatus: undefined
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  recId: undefined,
  rotatingDrumNo: undefined,
  rollerNoTr: undefined,
  asmatNo: undefined,
  asmatName: undefined,
  deviceStatus: '0',
  destinationStation: '01',
  nominalDia: undefined,
  bearingSmall: undefined,
  bearingBig: undefined
})

const rules = {
  rotatingDrumNo: [{ required: true, message: '转鼓号不能为空', trigger: 'blur' }]
}

const getStationLabel = (station) => {
  const map = {
    '01': '备用', '02': '装配', '03': '加工', '04': '报废',
    '05': '拆辊', '06': '装辊', '07': '检查'
  }
  return map[station] || station || '-'
}

const getStationTagType = (station) => {
  const map = {
    '01': 'info', '02': 'warning', '03': '', '04': 'danger',
    '05': 'info', '06': 'success', '07': 'warning'
  }
  return map[station] || 'info'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const res = await listDrum(queryParams)
    drumList.value = res.rows
    total.value = res.total
  } finally {
    loading.value = false
  }
}

/** 搜索 */
const handleQuery = () => {
  queryParams.pageNum = 1
  getList()
}

/** 重置 */
const resetQuery = () => {
  queryRef.value.resetFields()
  handleQuery()
}

/** 多选 */
const handleSelectionChange = (selection) => {
  ids.value = selection.map(item => item.recId)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}

/** 新增 */
const handleAdd = () => {
  reset()
  dialogTab.value = 'main'
  dialog.visible = true
  dialog.title = '新增转鼓'
}

/** 修改 */
const handleUpdate = (row) => {
  reset()
  dialogTab.value = 'main'
  const recId = row.recId || ids.value[0]
  getDrum(recId).then(res => {
    Object.assign(form, res.data)
    componentList.value = res.data.components || []
    dialog.visible = true
    dialog.title = '修改转鼓'
  })
}

/** 提交 */
const submitForm = () => {
  drumRef.value.validate(valid => {
    if (valid) {
      if (form.recId) {
        updateDrum(form).then(() => {
          ElMessage.success('修改成功')
          dialog.visible = false
          getList()
        })
      } else {
        insertDrum(form).then(() => {
          ElMessage.success('新增成功')
          dialog.visible = false
          getList()
        })
      }
    }
  })
}

/** 报废 */
const handleScrap = (row) => {
  const recIds = row.recId || ids.value.join(',')
  ElMessageBox.confirm('是否确认报废选中的设备？报废后不可恢复！', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return scrapDrum(recIds)
  }).then(() => {
    getList()
    ElMessage.success('报废成功')
  }).catch(() => {})
}

/** 上线 */
const handleOnline = () => {
  const recIds = ids.value.join(',')
  ElMessageBox.confirm('是否确认上线选中的设备？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return onlineDrum(recIds)
  }).then(() => {
    getList()
    ElMessage.success('上线成功')
  }).catch(() => {})
}

/** 下线 */
const handleOffline = () => {
  const recIds = ids.value.join(',')
  ElMessageBox.confirm('是否确认下线选中的设备？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return offlineDrum(recIds)
  }).then(() => {
    getList()
    ElMessage.success('下线成功')
  }).catch(() => {})
}

/** 导出 */
const handleExport = () => {
  ElMessage.info('导出功能开发中')
}

/** 取消 */
const cancel = () => {
  dialog.visible = false
  reset()
}

/** 重置表单 */
const reset = () => {
  form.recId = undefined
  form.rotatingDrumNo = undefined
  form.rollerNoTr = undefined
  form.asmatNo = undefined
  form.asmatName = undefined
  form.deviceStatus = '0'
  form.destinationStation = '01'
  form.nominalDia = undefined
  form.bearingSmall = undefined
  form.bearingBig = undefined
  componentList.value = []
  if (drumRef.value) {
    drumRef.value.resetFields()
  }
}

onMounted(() => {
  getList()
})
</script>

<style lang="scss" scoped>
.app-container {
  padding: 20px;
}
.mb8 {
  margin-bottom: 8px;
}
.dialog-footer {
  text-align: right;
}
</style>
