# 180工模具管理系统

衡钢180厂工模具管理系统，原为C++/WinForms架构（BM2/IPLAT4C框架），现迁移至Java前后端分离架构。

系统管理**定径机架、脱管机架、转鼓、穿孔辊、连轧机架、连轧辊、芯棒**等7类设备的全生命周期，实现从采购验收到生产下线的全流程管理。

---

## 技术栈

| 层次 | 技术 | 版本 |
|------|------|------|
| 后端框架 | Spring Boot | 3.2.5 |
| ORM框架 | MyBatis-Plus | 3.5.7 |
| 数据库 | Oracle | 11g+ |
| 连接池 | Druid | 1.2.20 |
| 前端框架 | Vue 3 | 3.4 |
| UI组件库 | Element Plus | 2.4 |
| 构建工具 | Vite | 5 |
| API文档 | SpringDoc OpenAPI (Swagger) | 2.3 |
| 工具库 | Hutool | 5.8.25 |

---

## 核心业务流程

```
采购验收 → 拆解 → 装配 → 加工 → 检查 → 备用 → 上线 → 生产 → 下线 → 加工（循环）
```

### 计划配置与排程

1. **生产规格配置**（TMZM20A1）：维护生产规格（品名、孔型、外径等），匹配穿孔机、连轧机架、定径机架、脱管机架
2. **匹配确认**：确认后写入TTMZM20CFG配置表
3. **计划排程**（TMZM20A2）：基于规格创建排程，挂合同，确认后将PLAN_NO写入各设备主信息表
4. **计划完成**：结束计划

### 设备生命周期

| 设备类型 | 拆解 | 装配 | 加工 | 上线/下线 | 拆装辊 |
|----------|------|------|------|-----------|--------|
| 定脱管机架 | TMZM2011 | TMZM2031 | TMZM2032 | - | - |
| 转鼓 | TMZM2011 | TMZM2051 | TMZM2052 | TMZM2050B/C | 支持 |
| 穿孔辊 | TMZM2011 | TMZM2051 | TMZM2052 | - | - |
| 连轧机架 | TMZM2011 | - | - | - | 支持 |
| 连轧辊 | TMZM2011 | - | - | - | - |

---

## 设备类型编码

| 编码 | 设备类型 | 数量 | 说明 |
|------|----------|------|------|
| 01 | 定径机架 | 14组 | 定径/脱管共用机架管理 |
| 02 | 脱管机架 | 6套 | 定径/脱管共用机架管理 |
| 03 | 转鼓 | 上/下转鼓 | 含穿孔辊组件 |
| 04 | 穿孔辊 | - | 穿孔辊组件 |
| 05 | 连轧机架 | 10组 | 含连轧辊组件 |
| 06 | 连轧辊 | - | 连轧辊组件 |
| 07 | 芯棒 | - | 限动芯棒 |

## 去向/状态编码

| 编码 | 去向 | 说明 |
|------|------|------|
| 01 | 备用 | 可上线状态 |
| 02 | 装配 | 等待装配 |
| 03 | 加工 | 等待加工 |
| 04 | 报废 | 已报废 |
| 05 | 拆辊 | 需要拆辊 |
| 06 | 装辊 | 需要装辊 |
| 07 | 检查 | 等待检查 |
| 98/99 | 计划上线 | 生产使用中 |

---

## 项目结构

```
tmzm-gmj/
├── tmzm-gmj-server/                    # Spring Boot 后端
│   └── src/main/java/com/hg/tmzm/
│       ├── TmzmApplication.java        # 启动类
│       ├── config/                     # 配置类
│       ├── common/                     # 公共模块
│       │   ├── domain/                 # 基础实体、分页
│       │   ├── response/               # 统一响应
│       │   ├── exception/              # 异常处理
│       │   └── constants/              # 常量定义
│       ├── entity/                     # 实体类（按设备类型分包）
│       │   ├── plan/                   # 计划配置实体
│       │   └── device/
│       │       ├── frame/              # 定脱管机架
│       │       ├── drum/               # 转鼓
│       │       ├── punchroll/          # 穿孔辊
│       │       └── rolling/            # 连轧机架/辊
│       ├── mapper/                     # MyBatis Mapper接口
│       ├── service/                    # 业务层
│       │   └── impl/                   # 业务实现
│       └── controller/                 # REST控制器
│
├── tmzm-gmj-ui/                        # Vue 3 前端
│   └── src/
│       ├── api/tmzm/                   # API接口
│       ├── views/tmzm/                 # 页面视图
│       │   ├── plan/                   # 计划模块页面
│       │   │   ├── config/             # 生产规格配置
│       │   │   ├── schedule/           # 计划排程
│       │   │   └── match/              # 匹配配置
│       │   └── device/                 # 设备模块页面
│       │       ├── frame/              # 定脱管机架管理
│       │       ├── drum/               # 转鼓管理
│       │       ├── punchroll/          # 穿孔辊管理
│       │       └── rolling/            # 连轧机架/辊管理
│       ├── router/                     # 路由配置
│       ├── store/                      # Pinia状态管理
│       └── components/                 # 公共组件
│
└── .run/                               # IDEA 运行配置
```

---

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- Node.js 18+
- Oracle 11g+ 数据库

### 后端启动

```bash
# 1. 修改数据库连接
# 编辑 tmzm-gmj-server/src/main/resources/application.yml

# 2. 启动
cd tmzm-gmj-server
mvn spring-boot:run
# 或在IDEA中使用 .run/后端启动 配置
```

### 前端启动

```bash
cd tmzm-gmj-ui
npm install
npm run dev
# 或在IDEA中使用 .run/前端启动 配置
```

### 同时启动

在IDEA中使用 `.run/前后端同时启动` 配置。

### 默认账号

| 账号 | 密码 |
|------|------|
| admin | admin123 |
| hgm0 | hgm0 |

---

## 核心数据库表

| 表名 | 用途 |
|------|------|
| TTMZM20A1 | 生产规格配置主表 |
| TTMZM20A2 | 计划排程主表 |
| TTMZM20A2O | 排程关联合同表 |
| TTMZM20CFG | 工模具匹配配置表 |
| TTMZM2011 | 库存信息表（来源于采购验收） |
| TTMZM2018 | 检查信息表 |
| TTMZM2030A1 | 定脱管机架主信息 |
| TTMZM2030A2 | 定脱管机架组成信息 |
| TTMZM2031 | 定脱管机架装配信息 |
| TTMZM2032 | 定脱管机架加工信息 |
| TTMZM2050A1 | 转鼓主信息 |
| TTMZM2050A2 | 转鼓组成信息 |
| TTMZM2051A1 | 穿孔辊主信息 |
| TTMZM2051A2 | 穿孔辊组成信息 |
| TTMZM2060A1 | 连轧机架主信息 |
| TTMZM2060A2 | 连轧机架组成信息 |
| TTMZM2061A1 | 连轧辊主信息 |
| TTMZM2061A2 | 连轧辊组成信息 |

---

## API接口

启动后端后访问 Swagger 文档：`http://localhost:8080/swagger-ui.html`

### 计划模块

| 模块 | 路径前缀 | 功能 |
|------|----------|------|
| 生产规格配置 | /tmzm/plan/config | 规格维护、匹配确认 |
| 计划排程 | /tmzm/plan/schedule | 排程维护、挂/脱合同、确认/撤销 |
| 匹配配置 | /tmzm/plan/match | 设备匹配配置 |

### 设备模块

| 模块 | 路径前缀 | 功能 |
|------|----------|------|
| 定脱管机架 | /tmzm/device/frame | 主信息、组成、装配、加工管理 |
| 转鼓 | /tmzm/device/drum | 主信息、上线/下线管理 |
| 穿孔辊 | /tmzm/device/punchroll | 穿孔辊管理 |
| 连轧机架/辊 | /tmzm/device/rolling | 连轧机架和连轧辊管理 |

---

## 贡献者

- [汤庄](https://github.com/Mman173)

## 许可证

MIT License
