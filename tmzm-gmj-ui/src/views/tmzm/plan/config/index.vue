<template>
  <div class="app-container">
    <!-- 搜索区域 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="定径延伸尺寸" prop="sizingElonSize">
        <el-input v-model="queryParams.sizingElonSize" placeholder="请输入定径延伸尺寸" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="品名" prop="prodMidCname">
        <el-input v-model="queryParams.prodMidCname" placeholder="请输入品名" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="rollStatus">
        <el-select v-model="queryParams.rollStatus" placeholder="请选择状态" clearable style="width: 200px">
          <el-option label="未确认" value="0" />
          <el-option label="已确认" value="1" />
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
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" />
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="configList" @selection-change="handleSelectionChange" stripe border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="recId" width="100" />
      <el-table-column label="定径延伸尺寸" align="center" prop="sizingElonSize" min-width="120" />
      <el-table-column label="孔型尺寸" align="center" prop="grooveSize" min-width="100" />
      <el-table-column label="品名" align="center" prop="prodMidCname" min-width="150" />
      <el-table-column label="产品编码" align="center" prop="prodMidCode" min-width="120" />
      <el-table-column label="轧辊直径" align="center" prop="rollDia" min-width="100" />
      <el-table-column label="芯棒直径" align="center" prop="mandrelDia" min-width="100" />
      <el-table-column label="状态" align="center" prop="rollStatus" width="90">
        <template #default="scope">
          <el-tag :type="scope.row.rollStatus === '1' ? 'success' : 'info'">
            {{ scope.row.rollStatus === '1' ? '已确认' : '未确认' }}
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
      <el-form ref="configRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="定径延伸尺寸" prop="sizingElonSize">
          <el-input v-model="form.sizingElonSize" placeholder="请输入定径延伸尺寸" />
        </el-form-item>
        <el-form-item label="孔型尺寸" prop="grooveSize">
          <el-input v-model="form.grooveSize" placeholder="请输入孔型尺寸" />
        </el-form-item>
        <el-form-item label="品名" prop="prodMidCname">
          <el-input v-model="form.prodMidCname" placeholder="请输入品名" />
        </el-form-item>
        <el-form-item label="产品编码" prop="prodMidCode">
          <el-input v-model="form.prodMidCode" placeholder="请输入产品编码" />
        </el-form-item>
        <el-form-item label="轧辊直径" prop="rollDia">
          <el-input v-model="form.rollDia" placeholder="请输入轧辊直径" />
        </el-form-item>
        <el-form-item label="芯棒直径" prop="mandrelDia">
          <el-input v-model="form.mandrelDia" placeholder="请输入芯棒直径" />
        </el-form-item>
        <el-form-item label="状态" prop="rollStatus">
          <el-radio-group v-model="form.rollStatus">
            <el-radio label="0">未确认</el-radio>
            <el-radio label="1">已确认</el-radio>
          </el-radio-group>
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
import { listConfig, getConfig, addConfig, updateConfig, delConfig } from '@/api/tmzm/plan/config'
import { ElMessage, ElMessageBox } from 'element-plus'

const configList = ref([])
const loading = ref(false)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)

const queryRef = ref(null)
const configRef = ref(null)

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  sizingElonSize: undefined,
  prodMidCname: undefined,
  rollStatus: undefined
})

const dialog = reactive({
  visible: false,
  title: ''
})

const form = reactive({
  recId: undefined,
  sizingElonSize: undefined,
  grooveSize: undefined,
  prodMidCname: undefined,
  prodMidCode: undefined,
  rollDia: undefined,
  mandrelDia: undefined,
  rollStatus: '0',
  specialReq: undefined
})

const rules = {
  sizingElonSize: [{ required: true, message: '定径延伸尺寸不能为空', trigger: 'blur' }],
  prodMidCname: [{ required: true, message: '品名不能为空', trigger: 'blur' }]
}

/** 查询列表 */
const getList = async () => {
  loading.value = true
  try {
    const res = await listConfig(queryParams)
    configList.value = res.rows
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
  dialog.title = '新增生产规格配置'
}

/** 修改 */
const handleUpdate = (row) => {
  reset()
  const recId = row.recId || ids.value[0]
  getConfig(recId).then(res => {
    Object.assign(form, res.data)
    dialog.visible = true
    dialog.title = '修改生产规格配置'
  })
}

/** 提交 */
const submitForm = () => {
  configRef.value.validate(valid => {
    if (valid) {
      if (form.recId) {
        updateConfig(form).then(() => {
          ElMessage.success('修改成功')
          dialog.visible = false
          getList()
        })
      } else {
        addConfig(form).then(() => {
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
    return delConfig(recIds)
  }).then(() => {
    getList()
    ElMessage.success('删除成功')
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
  form.sizingElonSize = undefined
  form.grooveSize = undefined
  form.prodMidCname = undefined
  form.prodMidCode = undefined
  form.rollDia = undefined
  form.mandrelDia = undefined
  form.rollStatus = '0'
  form.specialReq = undefined
  if (configRef.value) {
    configRef.value.resetFields()
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
