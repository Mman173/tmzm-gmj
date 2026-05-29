<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="规格序号" prop="seqNo1">
        <el-input v-model="queryParams.seqNo1" placeholder="请输入规格序号" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-select v-model="queryParams.deviceType" placeholder="请选择设备类型" clearable style="width: 200px">
          <el-option label="定径" value="01" />
          <el-option label="脱管" value="02" />
          <el-option label="转鼓" value="03" />
          <el-option label="穿孔辊" value="04" />
          <el-option label="连轧机架" value="05" />
          <el-option label="连轧辊" value="06" />
          <el-option label="芯棒" value="07" />
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
        <el-button type="primary" plain icon="Edit" :disabled="single" @click="handleEdit">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Check" @click="handleSave">保存</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="matchList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="规格序号" align="center" prop="seqNo1" width="100" />
      <el-table-column label="设备类型" align="center" prop="deviceType" width="100">
        <template #default="scope">
          {{ getDeviceTypeLabel(scope.row.deviceType) }}
        </template>
      </el-table-column>
      <el-table-column label="机架类型" align="center" prop="frameType" width="100" />
      <el-table-column label="机架号" align="center" prop="standNo" width="100" />
      <el-table-column label="装配编号" align="center" prop="asmatNo" min-width="120" />
      <el-table-column label="装配名称" align="center" prop="asmatName" min-width="150" />
      <el-table-column label="分支数" align="center" prop="branchesNumber" width="90" />
      <el-table-column label="标准数量" align="center" prop="standardNumber" width="90" />
      <el-table-column label="去向工位" align="center" prop="destinationStation" width="100" />
      <el-table-column label="操作" align="center" width="120" fixed="right">
        <template #default="scope">
          <el-button type="text" icon="Edit" @click="handleEditRow(scope.row)">编辑</el-button>
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

    <!-- 编辑对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="650px" append-to-body>
      <el-form ref="matchRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="规格序号" prop="seqNo1">
          <el-input v-model="form.seqNo1" placeholder="请输入规格序号" />
        </el-form-item>
        <el-form-item label="设备类型" prop="deviceType">
          <el-select v-model="form.deviceType" placeholder="请选择设备类型" style="width: 100%">
            <el-option label="定径" value="01" />
            <el-option label="脱管" value="02" />
            <el-option label="转鼓" value="03" />
            <el-option label="穿孔辊" value="04" />
            <el-option label="连轧机架" value="05" />
            <el-option label="连轧辊" value="06" />
            <el-option label="芯棒" value="07" />
          </el-select>
        </el-form-item>
        <el-form-item label="机架类型" prop="frameType">
          <el-input v-model="form.frameType" placeholder="请输入机架类型" />
        </el-form-item>
        <el-form-item label="机架号" prop="standNo">
          <el-input v-model="form.standNo" placeholder="请输入机架号" />
        </el-form-item>
        <el-form-item label="装配编号" prop="asmatNo">
          <el-input v-model="form.asmatNo" placeholder="请输入装配编号" />
        </el-form-item>
        <el-form-item label="装配名称" prop="asmatName">
          <el-input v-model="form.asmatName" placeholder="请输入装配名称" />
        </el-form-item>
        <el-form-item label="分支数" prop="branchesNumber">
          <el-input-number v-model="form.branchesNumber" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="标准数量" prop="standardNumber">
          <el-input-number v-model="form.standardNumber" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="去向工位" prop="destinationStation">
          <el-input v-model="form.destinationStation" placeholder="请输入去向工位" />
        </el-form-item>
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
import { listMatch, getMatch, updateMatch } from '@/api/tmzm/plan/match'
import { ElMessage, ElMessageBox } from 'element-plus'

const matchList = ref([])
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)

const queryRef = ref(null)
const matchRef = ref(null)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  seqNo1: undefined,
  deviceType: undefined
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  recId: undefined,
  seqNo1: undefined,
  deviceType: undefined,
  frameType: undefined,
  standNo: undefined,
  asmatNo: undefined,
  asmatName: undefined,
  branchesNumber: undefined,
  standardNumber: undefined,
  destinationStation: undefined
})

const rules = {
  seqNo1: [{ required: true, message: '规格序号不能为空', trigger: 'blur' }],
  deviceType: [{ required: true, message: '设备类型不能为空', trigger: 'change' }]
}

const getDeviceTypeLabel = (type) => {
  const map = {
    '01': '定径',
    '02': '脱管',
    '03': '转鼓',
    '04': '穿孔辊',
    '05': '连轧机架',
    '06': '连轧辊',
    '07': '芯棒'
  }
  return map[type] || type
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const res = await listMatch(queryParams)
    matchList.value = res.rows
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

/** 编辑 */
const handleEdit = () => {
  if (ids.value.length !== 1) {
    ElMessage.warning('请选择一条数据')
    return
  }
  handleEditRow({ recId: ids.value[0] })
}

/** 编辑行 */
const handleEditRow = (row) => {
  reset()
  getMatch(row.recId).then(res => {
    Object.assign(form, res.data)
    dialog.visible = true
    dialog.title = '编辑匹配配置'
  })
}

/** 保存 */
const handleSave = () => {
  ElMessage.success('保存成功')
}

/** 提交 */
const submitForm = () => {
  matchRef.value.validate(valid => {
    if (valid) {
      updateMatch(form).then(() => {
        ElMessage.success('修改成功')
        dialog.visible = false
        getList()
      })
    }
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
  form.seqNo1 = undefined
  form.deviceType = undefined
  form.frameType = undefined
  form.standNo = undefined
  form.asmatNo = undefined
  form.asmatName = undefined
  form.branchesNumber = undefined
  form.standardNumber = undefined
  form.destinationStation = undefined
  if (matchRef.value) {
    matchRef.value.resetFields()
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
