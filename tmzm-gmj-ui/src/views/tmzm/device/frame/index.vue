<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="机架号" prop="standNo">
        <el-input v-model="queryParams.standNo" placeholder="请输入机架号" clearable style="width: 200px" @keyup.enter="handleQuery" />
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
      <el-form-item label="设备类型" prop="deviceType">
        <el-select v-model="queryParams.deviceType" placeholder="请选择设备类型" clearable style="width: 200px">
          <el-option label="定径" value="01" />
          <el-option label="脱管" value="02" />
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
        <el-button type="warning" plain icon="Switch" :disabled="single" @click="handleChangeStation">变更去向</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="View" :disabled="single" @click="handleViewHistory">查看履历</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="frameList" @selection-change="handleSelectionChange" @current-change="handleCurrentRowChange" stripe border highlight-current-row>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="机架号" align="center" prop="standNo" width="100" />
      <el-table-column label="装配编号" align="center" prop="asmatNo" min-width="120" />
      <el-table-column label="装配名称" align="center" prop="asmatName" min-width="150" />
      <el-table-column label="规格" align="center" prop="spec" width="100" />
      <el-table-column label="型号规格" align="center" prop="typeSpec" width="120" />
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
      <el-table-column label="供应商" align="center" prop="supplierName" min-width="120" />
      <el-table-column label="计划号" align="center" prop="planNo" min-width="120" />
      <el-table-column label="操作" align="center" width="180" fixed="right">
        <template #default="scope">
          <el-button type="text" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="View" @click="handleViewHistory(scope.row)">履历</el-button>
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

    <!-- 详情标签页 -->
    <el-tabs v-model="activeTab" v-if="currentRow" style="margin-top: 20px">
      <el-tab-pane label="组成信息" name="detail">
        <el-table :data="detailList" stripe border v-loading="detailLoading">
          <el-table-column label="序号" type="index" width="60" align="center" />
          <el-table-column label="零件编号" align="center" prop="partNo" min-width="120" />
          <el-table-column label="零件名称" align="center" prop="partName" min-width="120" />
          <el-table-column label="数量" align="center" prop="quantity" width="80" />
          <el-table-column label="材质" align="center" prop="material" width="100" />
          <el-table-column label="备注" align="center" prop="remark" min-width="150" />
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="装配信息" name="assembly">
        <el-table :data="assemblyList" stripe border v-loading="assemblyLoading">
          <el-table-column label="序号" type="index" width="60" align="center" />
          <el-table-column label="装配日期" align="center" prop="assemblyDate" width="120" />
          <el-table-column label="装配人员" align="center" prop="assemblyPerson" width="100" />
          <el-table-column label="装配结果" align="center" prop="assemblyResult" width="100" />
          <el-table-column label="备注" align="center" prop="remark" min-width="150" />
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="加工信息" name="processing">
        <el-table :data="processingList" stripe border v-loading="processingLoading">
          <el-table-column label="序号" type="index" width="60" align="center" />
          <el-table-column label="加工日期" align="center" prop="processingDate" width="120" />
          <el-table-column label="加工类型" align="center" prop="processingType" width="100" />
          <el-table-column label="加工人员" align="center" prop="processingPerson" width="100" />
          <el-table-column label="加工结果" align="center" prop="processingResult" width="100" />
          <el-table-column label="备注" align="center" prop="remark" min-width="150" />
        </el-table>
      </el-tab-pane>
    </el-tabs>

    <!-- 新增/修改对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="700px" append-to-body>
      <el-form ref="frameRef" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="机架号" prop="standNo">
              <el-input v-model="form.standNo" placeholder="请输入机架号" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="装配编号" prop="asmatNo">
              <el-input v-model="form.asmatNo" placeholder="请输入装配编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="装配名称" prop="asmatName">
              <el-input v-model="form.asmatName" placeholder="请输入装配名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规格" prop="spec">
              <el-input v-model="form.spec" placeholder="请输入规格" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="型号规格" prop="typeSpec">
              <el-input v-model="form.typeSpec" placeholder="请输入型号规格" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备类型" prop="deviceType">
              <el-select v-model="form.deviceType" placeholder="请选择设备类型" style="width: 100%">
                <el-option label="定径" value="01" />
                <el-option label="脱管" value="02" />
              </el-select>
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
            <el-form-item label="分支数" prop="branchesNumber">
              <el-input-number v-model="form.branchesNumber" :min="0" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商" prop="supplierName">
              <el-input v-model="form.supplierName" placeholder="请输入供应商" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="计划号" prop="planNo">
          <el-input v-model="form.planNo" placeholder="请输入计划号" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 变更去向对话框 -->
    <el-dialog title="变更去向" v-model="stationDialog.visible" width="500px" append-to-body>
      <el-form ref="stationRef" :model="stationForm" :rules="stationRules" label-width="100px">
        <el-form-item label="当前去向">
          <el-tag :type="getStationTagType(stationForm.currentStation)">
            {{ getStationLabel(stationForm.currentStation) }}
          </el-tag>
        </el-form-item>
        <el-form-item label="变更去向" prop="destinationStation">
          <el-select v-model="stationForm.destinationStation" placeholder="请选择去向工位" style="width: 100%">
            <el-option label="备用" value="01" />
            <el-option label="装配" value="02" />
            <el-option label="加工" value="03" />
            <el-option label="报废" value="04" />
            <el-option label="拆辊" value="05" />
            <el-option label="装辊" value="06" />
            <el-option label="检查" value="07" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitStation">确 定</el-button>
          <el-button @click="stationDialog.visible = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 履历对话框 -->
    <el-dialog title="设备履历" v-model="historyDialog.visible" width="800px" append-to-body>
      <el-table :data="historyList" stripe border v-loading="historyLoading">
        <el-table-column label="操作时间" align="center" prop="operateTime" width="160" />
        <el-table-column label="操作类型" align="center" prop="operateType" width="100" />
        <el-table-column label="操作人" align="center" prop="operator" width="100" />
        <el-table-column label="原状态" align="center" prop="oldStatus" width="100" />
        <el-table-column label="新状态" align="center" prop="newStatus" width="100" />
        <el-table-column label="备注" align="center" prop="remark" min-width="150" show-overflow-tooltip />
      </el-table>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { listFrame, getFrame, insertFrame, updateFrame, scrapFrame, changeDestination, historyFrame } from '@/api/tmzm/device/frame'
import { listFrameDetail } from '@/api/tmzm/device/frameDetail'
import { listFrameAssembly } from '@/api/tmzm/device/frameAssembly'
import { listFrameProcessing } from '@/api/tmzm/device/frameProcessing'
import { ElMessage, ElMessageBox } from 'element-plus'

const frameList = ref([])
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const currentRow = ref(null)
const activeTab = ref('detail')

// 详情相关
const detailList = ref([])
const detailLoading = ref(false)
const assemblyList = ref([])
const assemblyLoading = ref(false)
const processingList = ref([])
const processingLoading = ref(false)

// 履历相关
const historyList = ref([])
const historyLoading = ref(false)

const queryRef = ref(null)
const frameRef = ref(null)
const stationRef = ref(null)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  standNo: undefined,
  deviceStatus: undefined,
  destinationStation: undefined,
  deviceType: undefined
})

const dialog = reactive({
  visible: false,
  title: ''
})

const stationDialog = reactive({
  visible: false
})

const historyDialog = reactive({
  visible: false
})

const form = reactive({
  recId: undefined,
  standNo: undefined,
  asmatNo: undefined,
  asmatName: undefined,
  spec: undefined,
  typeSpec: undefined,
  deviceStatus: '0',
  destinationStation: '01',
  branchesNumber: undefined,
  supplierName: undefined,
  planNo: undefined,
  deviceType: undefined
})

const stationForm = reactive({
  recId: undefined,
  currentStation: undefined,
  destinationStation: undefined
})

const rules = {
  standNo: [{ required: true, message: '机架号不能为空', trigger: 'blur' }],
  deviceType: [{ required: true, message: '设备类型不能为空', trigger: 'change' }]
}

const stationRules = {
  destinationStation: [{ required: true, message: '请选择去向工位', trigger: 'change' }]
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
    const res = await listFrame(queryParams)
    frameList.value = res.rows
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

/** 当前行变化 */
const handleCurrentRowChange = (row) => {
  currentRow.value = row
  if (row) {
    loadDetail(row.recId)
    loadAssembly(row.recId)
    loadProcessing(row.recId)
  }
}

/** 加载组成信息 */
const loadDetail = async (frameId) => {
  detailLoading.value = true
  try {
    const res = await listFrameDetail({ frameId })
    detailList.value = res.rows || []
  } finally {
    detailLoading.value = false
  }
}

/** 加载装配信息 */
const loadAssembly = async (frameId) => {
  assemblyLoading.value = true
  try {
    const res = await listFrameAssembly({ frameId })
    assemblyList.value = res.rows || []
  } finally {
    assemblyLoading.value = false
  }
}

/** 加载加工信息 */
const loadProcessing = async (frameId) => {
  processingLoading.value = true
  try {
    const res = await listFrameProcessing({ frameId })
    processingList.value = res.rows || []
  } finally {
    processingLoading.value = false
  }
}

/** 新增 */
const handleAdd = () => {
  reset()
  dialog.visible = true
  dialog.title = '新增定脱管机架'
}

/** 修改 */
const handleUpdate = (row) => {
  reset()
  const recId = row.recId || ids.value[0]
  getFrame(recId).then(res => {
    Object.assign(form, res.data)
    dialog.visible = true
    dialog.title = '修改定脱管机架'
  })
}

/** 提交 */
const submitForm = () => {
  frameRef.value.validate(valid => {
    if (valid) {
      if (form.recId) {
        updateFrame(form).then(() => {
          ElMessage.success('修改成功')
          dialog.visible = false
          getList()
        })
      } else {
        insertFrame(form).then(() => {
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
    return scrapFrame(recIds)
  }).then(() => {
    getList()
    ElMessage.success('报废成功')
  }).catch(() => {})
}

/** 变更去向 */
const handleChangeStation = () => {
  if (ids.value.length !== 1) {
    ElMessage.warning('请选择一条数据')
    return
  }
  const row = frameList.value.find(item => item.recId === ids.value[0])
  stationForm.recId = row.recId
  stationForm.currentStation = row.destinationStation
  stationForm.destinationStation = undefined
  stationDialog.visible = true
}

/** 提交变更去向 */
const submitStation = () => {
  stationRef.value.validate(valid => {
    if (valid) {
      changeDestination(stationForm).then(() => {
        ElMessage.success('变更成功')
        stationDialog.visible = false
        getList()
      })
    }
  })
}

/** 查看履历 */
const handleViewHistory = (row) => {
  const recId = row.recId || ids.value[0]
  historyLoading.value = true
  historyDialog.visible = true
  historyFrame(recId).then(res => {
    historyList.value = res.rows || []
  }).finally(() => {
    historyLoading.value = false
  })
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
  form.asmatNo = undefined
  form.asmatName = undefined
  form.spec = undefined
  form.typeSpec = undefined
  form.deviceStatus = '0'
  form.destinationStation = '01'
  form.branchesNumber = undefined
  form.supplierName = undefined
  form.planNo = undefined
  form.deviceType = undefined
  if (frameRef.value) {
    frameRef.value.resetFields()
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
