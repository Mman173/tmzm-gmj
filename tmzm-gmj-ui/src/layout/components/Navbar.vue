<template>
  <div class="navbar">
    <div class="hamburger-container" @click="toggleSidebar">
      <el-icon :size="20">
        <Fold v-if="sidebar.opened" />
        <Expand v-else />
      </el-icon>
    </div>

    <div class="right-menu">
      <el-dropdown trigger="click" @command="handleCommand">
        <div class="avatar-wrapper">
          <el-avatar :size="30" icon="UserFilled" />
          <span class="user-name">{{ name }}</span>
          <el-icon><CaretBottom /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command="logout">
              <el-icon><SwitchButton /></el-icon>
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import useAppStore from '@/store/modules/app'
import useUserStore from '@/store/modules/user'
import { ElMessageBox } from 'element-plus'

const router = useRouter()
const appStore = useAppStore()
const userStore = useUserStore()

const sidebar = computed(() => appStore.sidebar)
const name = computed(() => userStore.name)

const toggleSidebar = () => {
  appStore.toggleSidebar()
}

const handleCommand = (command) => {
  if (command === 'logout') {
    ElMessageBox.confirm('确定注销并退出系统吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      userStore.logoutAction().then(() => {
        router.push('/login')
      })
    }).catch(() => {})
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
}
.hamburger-container {
  cursor: pointer;
  display: flex;
  align-items: center;
}
.right-menu {
  display: flex;
  align-items: center;
}
.avatar-wrapper {
  display: flex;
  align-items: center;
  cursor: pointer;
  gap: 8px;
}
.user-name {
  font-size: 14px;
  color: #333;
}
</style>
