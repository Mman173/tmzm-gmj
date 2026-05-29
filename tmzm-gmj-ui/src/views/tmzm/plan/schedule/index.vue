<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="计划号" prop="planNo">
        <el-input v-model="queryParams.planNo" placeholder="请输入计划号" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="合同号" prop="orderNo">
        <el-input v-model="queryParams.orderNo" placeholder="请输入合同号" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="rollStatus">
        <el-select v-model="queryParams.rollStatus" placeholder="请选择状态" clearable style="width: 200px">
          <el-option label="待确认" value="0" />
          <el-option label="已确认" value="1" />
          <el-option label="已取消" value="2" />
          <el-option label="已完成" value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围">
        <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD" style="width: 240px" />
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
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Check" :disabled="multiple" @click="handleConfirm">确认排程</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Close" :disabled="multiple" @click="handleCancelConfirm">撤销确认</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Finished" :disabled="multiple" @click="handleComplete">计划完成</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="计划号" align="center" prop="planNo" min-width="130" />
      <el-table-column label="合同号" align="center" prop="orderNo" min-width="130" />
      <el-table-column label="定径延伸尺寸" align="center" prop="sizingElonSize" min-width="120" />
      <el-table-column label="品名" align="center" prop="prodMidCname" min-width="150" />
      <el-table-column label="开始生产时间" align="center" prop="startProdTime" width="160" />
      <el-table-column label="状态" align="center" prop="rollStatus" width="100">
        <template #default="scope">
          <el-tag :type="getStatusType(scope.row.rollStatus)">
            {{ getStatusLabel(scope.row.rollStatus) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="确认时间" align="center" prop="confirmTime" width="160" />
      <el-table-column label="特殊要求" align="center" prop="specialReq" min-width="150" show-overflow-tooltip />
      <el-table-column label="操作" align="center" width="180" fixed="right">
        <template #default="scope">
          <el-button type="text" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button type="text" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
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
      <el-form ref="scheduleRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="计划号" prop="planNo">
          <el-input v-model="form.planNo" placeholder="请输入计划号" />
        </el-form-item>
        <el-form-item label="合同号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入合同号" />
        </el-form-item>
        <el-form-item label="定径延伸尺寸" prop="sizingElonSize">
          <el-input v-model="form.sizingElonSize" placeholder="请输入定径延伸尺寸" />
        </el-form-item>
        <el-form-item label="品名" prop="prodMidCname">
          <el-input v-model="form.prodMidCname" placeholder="请输入品名" />
        </el-form-item>
        <el-form-item label="开始生产时间" prop="startProdTime">
          <el-date-picker v-model="form.startProdTime" type="datetime" placeholder="选择开始生产时间" value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" />
        </el-form-item>
        <el-form-item label="状态" prop="rollStatus">
          <el-select v-model="form.rollStatus" placeholder="请选择状态" style="width: 100%">
            <el-option label="待确认" value="0" />
            <el-option label="已确认" value="1" />
            <el-option label="已取消" value="2" />
            <el-option label="已完成" value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="特殊要求" prop="specialReq">
          <el-input v-model="form.specialReq" type="textarea" :rows="3" placeholder="请输入特殊要求" />
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
import {
  listSchedule, getSchedule, addSchedule, updateSchedule, delSchedule,
  confirmSchedule, cancelSchedule, completeSchedule
} from '@/api/tmzm/plan/schedule'
import { ElMessage, ElMessageBox } from 'element-plus'

const scheduleList = ref([])
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const dateRange = ref([])

const queryRef = ref(null)
const scheduleRef = ref(null)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  planNo: undefined,
  orderNo: undefined,
  rollStatus: undefined
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  recId: undefined,
  planNo: undefined,
  orderNo: undefined,
  sizingElonSize: undefined,
  prodMidCname: undefined,
  startProdTime: undefined,
  rollStatus: '0',
  specialReq: undefined
})

const rules = {
  planNo: [{ required: true, message: '计划号不能为空', trigger: 'blur' }],
  orderNo: [{ required: true, message: '合同号不能为空', trigger: 'blur' }]
}

const getStatusType = (status) => {
  const map = { '0': 'info', '1': 'success', '2': 'danger', '3': '' }
  return map[status] || 'info'
}

const getStatusLabel = (status) => {
  const map = { '0': '待确认', '1': '已确认', '2': '已取消', '3': '已完成' }
  return map[status] || '未知'
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const params = { ...queryParams }
    if (dateRange.value && dateRange.value.length === 2) {
      params.beginTime = dateRange.value[0]
      params.endTime = dateRange.value[1]
    }
    const res = await listSchedule(params)
    scheduleList.value = res.rows
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
  dateRange.value = []
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
  dialog.title = '新增计划排程'
}

/** 修改 */
const handleUpdate = (row) => {
  reset()
  const recId = row.recId || ids.value[0]
  getSchedule(recId).then(res => {
    Object.assign(form, res.data)
    dialog.visible = true
    dialog.title = '修改计划排程'
  })
}

/** 提交 */
const submitForm = () => {
  scheduleRef.value.validate(valid => {
    if (valid) {
      if (form.recId) {
        updateSchedule(form).then(() => {
          ElMessage.success('修改成功')
          dialog.visible = false
          getList()
        })
      } else {
        addSchedule(form).then(() => {
          ElMessage.success('新增成功')
          dialog.visible = false
          getList()
        })
      }
    }
  })
}

/** 删除 */
const handleDelete = (row) => {
  const recIds = row.recId || ids.value.join(',')
  ElMessageBox.confirm('是否确认删除选中的数据项？', '警告', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return delSchedule(recIds)
  }).then(() => {
    getList()
    ElMessage.success('删除成功')
  }).catch(() => {})
}

/** 确认排程 */
const handleConfirm = () => {
  const recIds = ids.value.join(',')
  ElMessageBox.confirm('是否确认选中的计划排程？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return confirmSchedule(recIds)
  }).then(() => {
    getList()
    ElMessage.success('确认成功')
  }).catch(() => {})
}

/** 撤销确认 */
const handleCancelConfirm = () => {
  const recIds = ids.value.join(',')
  ElMessageBox.confirm('是否撤销确认选中的计划排程？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return cancelSchedule(recIds)
  }).then(() => {
    getList()
    ElMessage.success('撤销成功')
  }).catch(() => {})
}

/** 计划完成 */
const handleComplete = () => {
  const recIds = ids.value.join(',')
  ElMessageBox.confirm('是否确认完成选中的计划排程？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    return completeSchedule(recIds)
  }).then(() => {
    getList()
    ElMessage.success('操作成功')
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
  form.planNo = undefined
  form.orderNo = undefined
  form.sizingElonSize = undefined
  form.prodMidCname = undefined
  form.startProdTime = undefined
  form.rollStatus = '0'
  form.specialReq = undefined
  if (scheduleRef.value) {
    scheduleRef.value.resetFields()
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
