<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="机架号" prop="standNo">
        <el-input v-model="queryParams.standNo" placeholder="请输入机架号" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-select v-model="queryParams.deviceType" placeholder="请选择设备类型" clearable style="width: 200px">
          <el-option label="连轧机架" value="05" />
          <el-option label="连轧辊" value="06" />
        </el-select>
      </el-form-item>
      <el-form-item label="设备状态" prop="deviceStatus">
        <el-select v-model="queryParams.deviceStatus" placeholder="请选择设备状态" clearable style="width: 200px">
          <el-option label="离线" value="0" />
          <el-option label="在线" value="1" />
        </el-select>
      </el-form-item>
      <el-form-item label="去向工位" prop="destinationStation">
        <el-select v-model="queryParams.destinationStation" placeholder="请选择去向工位" clearable style="width: 200px">
          <el-option label="备用" value="01" />
          <el-option label="装配" value="02" />
          <el-option label="加工" value="03" />
          <el-option label="报废" value="04" />
          <el-option label="拆辊" value="05" />
          <el-option label="装辊" value="06" />
          <el-option label="检查" value="07" />
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
        <el-button type="warning" plain icon="Bottom" :disabled="multiple" @click="handleOffline">下线</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="rollingList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机架号" align="center" prop="standNo" width="100" />
      <el-table-column label="轧辊号" align="center" prop="rollerNoTr" width="120" />
      <el-table-column label="装配编号" align="center" prop="asmatNo" min-width="120" />
      <el-table-column label="装配名称" align="center" prop="asmatName" min-width="150" />
      <el-table-column label="设备类型" align="center" prop="deviceType" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.deviceType === '05' ? '' : 'warning'">
            {{ scope.row.deviceType === '05' ? '连轧机架' : '连轧辊' }}
          </el-tag>
        </template>
      </el-table-column>
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
      <el-table-column label="分支数" align="center" prop="branchesNumber" width="80" />
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
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="650px" append-to-body>
      <el-form ref="rollingRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机架号" prop="standNo">
              <el-input v-model="form.standNo" placeholder="请输入机架号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="轧辊号" prop="rollerNoTr">
              <el-input v-model="form.rollerNoTr" placeholder="请输入轧辊号" />
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
            <el-form-item label="设备类型" prop="deviceType">
              <el-select v-model="form.deviceType" placeholder="请选择设备类型" style="width: 100%">
                <el-option label="连轧机架" value="05" />
                <el-option label="连轧辊" value="06" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备状态" prop="deviceStatus">
              <el-select v-model="form.deviceStatus" placeholder="请选择设备状态" style="width: 100%">
                <el-option label="离线" value="0" />
                <el-option label="在线" value="1" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
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
          <el-col :span="12">
            <el-form-item label="分支数" prop="branchesNumber">
              <el-input-number v-model="form.branchesNumber" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
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
import { listRolling, getRolling, insertRolling, updateRolling, scrapRolling, offlineRolling } from '@/api/tmzm/device/rolling'
import { ElMessage, ElMessageBox } from 'element-plus'

const rollingList = ref([])
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)

const queryRef = ref(null)
const rollingRef = ref(null)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  standNo: undefined,
  deviceType: undefined,
  deviceStatus: undefined,
  destinationStation: undefined
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  recId: undefined,
  standNo: undefined,
  rollerNoTr: undefined,
  asmatNo: undefined,
  asmatName: undefined,
  deviceType: '05',
  deviceStatus: '0',
  destinationStation: '01',
  branchesNumber: undefined
})

const rules = {
  standNo: [{ required: true, message: '机架号不能为空', trigger: 'blur' }],
  deviceType: [{ required: true, message: '设备类型不能为空', trigger: 'change' }]
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
    const res = await listRolling(queryParams)
    rollingList.value = res.rows
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
  dialog.visible = true
  dialog.title = '新增连轧机架/辊'
}

/** 修改 */
const handleUpdate = (row) => {
  reset()
  const recId = row.recId || ids.value[0]
  getRolling(recId).then(res => {
    Object.assign(form, res.data)
    dialog.visible = true
    dialog.title = '修改连轧机架/辊'
  })
}

/** 提交 */
const submitForm = () => {
  rollingRef.value.validate(valid => {
    if (valid) {
      if (form.recId) {
        updateRolling(form).then(() => {
          ElMessage.success('修改成功')
          dialog.visible = false
          getList()
        })
      } else {
        insertRolling(form).then(() => {
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
    return scrapRolling(recIds)
  }).then(() => {
    getList()
    ElMessage.success('报废成功')
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
    return offlineRolling(recIds)
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
  form.standNo = undefined
  form.rollerNoTr = undefined
  form.asmatNo = undefined
  form.asmatName = undefined
  form.deviceType = '05'
  form.deviceStatus = '0'
  form.destinationStation = '01'
  form.branchesNumber = undefined
  if (rollingRef.value) {
    rollingRef.value.resetFields()
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
