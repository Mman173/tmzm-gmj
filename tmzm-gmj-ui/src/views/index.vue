<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card class="welcome-card">
          <template #header>
            <div class="card-header">
              <span>欢迎使用工模具管理系统</span>
            </div>
          </template>
          <div class="welcome-content">
            <p>本系统用于管理定脱管机架、转鼓、穿孔辊、连轧机架/辊等工模具设备的全生命周期管理。</p>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-title">定脱管机架</div>
          <div class="stat-icon" style="background: #409eff">
            <el-icon :size="32"><Box /></el-icon>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-title">转鼓</div>
          <div class="stat-icon" style="background: #67c23a">
            <el-icon :size="32"><RefreshRight /></el-icon>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-title">穿孔辊</div>
          <div class="stat-icon" style="background: #e6a23c">
            <el-icon :size="32"><Odometer /></el-icon>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-title">连轧机架/辊</div>
          <div class="stat-icon" style="background: #f56c6c">
            <el-icon :size="32"><DataLine /></el-icon>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>快捷入口</span>
            </div>
          </template>
          <el-space wrap>
            <el-button type="primary" @click="$router.push('/tmzm/plan/config')">生产规格配置</el-button>
            <el-button type="success" @click="$router.push('/tmzm/plan/schedule')">计划排程</el-button>
            <el-button type="warning" @click="$router.push('/tmzm/device/frame')">定脱管机架</el-button>
            <el-button type="danger" @click="$router.push('/tmzm/device/drum')">转鼓管理</el-button>
            <el-button type="info" @click="$router.push('/tmzm/device/punchroll')">穿孔辊管理</el-button>
            <el-button @click="$router.push('/tmzm/device/rolling')">连轧机架/辊</el-button>
          </el-space>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>系统信息</span>
            </div>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="系统名称">工模具管理系统</el-descriptions-item>
            <el-descriptions-item label="版本">V1.0.0</el-descriptions-item>
            <el-descriptions-item label="技术栈">Vue3 + Element Plus + Vite</el-descriptions-item>
            <el-descriptions-item label="后端">Spring Boot</el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
</script>

<style lang="scss" scoped>
.dashboard-container {
  padding: 10px;
}
.welcome-card {
  .card-header {
    font-size: 18px;
    font-weight: 600;
  }
}
.welcome-content {
  font-size: 14px;
  color: #666;
  line-height: 1.8;
}
.stat-card {
  text-align: center;
  .stat-title {
    font-size: 16px;
    font-weight: 600;
    color: #333;
    margin-bottom: 10px;
  }
  .stat-icon {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 60px;
    height: 60px;
    border-radius: 50%;
    color: #fff;
  }
}
.card-header {
  font-size: 16px;
  font-weight: 600;
}
</style>
