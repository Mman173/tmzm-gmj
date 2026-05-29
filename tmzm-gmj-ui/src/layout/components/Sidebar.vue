<template>
  <div class="sidebar">
    <div v-if="showLogo" class="sidebar-logo-container">
      <h1 class="sidebar-title">工模具管理系统</h1>
    </div>
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :collapse-transition="false"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        :unique-opened="true"
        mode="vertical"
        router
      >
        <el-menu-item index="/index">
          <el-icon><HomeFilled /></el-icon>
          <template #title>首页</template>
        </el-menu-item>

        <el-sub-menu index="/tmzm/plan">
          <template #title>
            <el-icon><Calendar /></el-icon>
            <span>计划管理</span>
          </template>
          <el-menu-item index="/tmzm/plan/config">
            <el-icon><Setting /></el-icon>
            <template #title>生产规格配置</template>
          </el-menu-item>
          <el-menu-item index="/tmzm/plan/schedule">
            <el-icon><Document /></el-icon>
            <template #title>计划排程</template>
          </el-menu-item>
          <el-menu-item index="/tmzm/plan/match">
            <el-icon><Connection /></el-icon>
            <template #title>匹配配置</template>
          </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="/tmzm/device">
          <template #title>
            <el-icon><SetUp /></el-icon>
            <span>设备管理</span>
          </template>
          <el-menu-item index="/tmzm/device/frame">
            <el-icon><Box /></el-icon>
            <template #title>定脱管机架管理</template>
          </el-menu-item>
          <el-menu-item index="/tmzm/device/drum">
            <el-icon><RefreshRight /></el-icon>
            <template #title>转鼓管理</template>
          </el-menu-item>
          <el-menu-item index="/tmzm/device/punchroll">
            <el-icon><Odometer /></el-icon>
            <template #title>穿孔辊管理</template>
          </el-menu-item>
          <el-menu-item index="/tmzm/device/rolling">
            <el-icon><DataLine /></el-icon>
            <template #title>连轧机架/辊管理</template>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import useAppStore from '@/store/modules/app'
import settings from '@/settings'

const route = useRoute()
const appStore = useAppStore()

const showLogo = settings.sidebarLogo
const activeMenu = computed(() => route.path)
const isCollapse = computed(() => !appStore.sidebar.opened)
</script>

<style lang="scss" scoped>
.sidebar {
  height: 100%;
  background-color: #304156;
}
.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  text-align: center;
  background-color: #2b2f3a;
  overflow: hidden;
}
.sidebar-title {
  color: #fff;
  font-size: 14px;
  margin: 0;
  font-weight: 600;
  white-space: nowrap;
}
.el-menu {
  border: none;
}
</style>
