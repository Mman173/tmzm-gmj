<template>
  <div class="right-toolbar">
    <el-row>
      <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
        <el-button circle icon="Search" @click="toggleSearch" />
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="刷新" placement="top">
        <el-button circle icon="Refresh" @click="refresh" />
      </el-tooltip>
    </el-row>
  </div>
</template>

<script setup>
const props = defineProps({
  showSearch: {
    type: Boolean,
    default: true
  }
})

const emit = defineEmits(['update:showSearch', 'queryTable'])

const toggleSearch = () => {
  emit('update:showSearch', !props.showSearch)
}

const refresh = () => {
  emit('queryTable')
}
</script>

<style lang="scss" scoped>
.right-toolbar {
  float: right;
  margin-left: 10px;
}
</style>
