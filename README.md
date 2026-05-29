# 工模具管理系统 (tmzm-gmj)

钢管工模具管理系统，用于计划管理和设备管理。

## 技术栈

**后端：**
- Java 17
- Spring Boot 3.2.5
- MyBatis-Plus 3.5.7
- Druid 连接池
- Oracle 数据库
- SpringDoc OpenAPI (Swagger)

**前端：**
- Vue 3.4
- Element Plus 2.4
- Vite 5
- Pinia 状态管理
- Vue Router

## 项目结构

```
tmzm-gmj/
├── tmzm-gmj-server/        # 后端 Spring Boot 项目
│   └── src/main/java/com/hg/tmzm/
│       ├── TmzmApplication.java
│       ├── controller/      # 控制器
│       ├── service/         # 业务层
│       ├── mapper/          # 数据访问层
│       ├── entity/          # 实体类
│       └── common/          # 公共类
├── tmzm-gmj-ui/            # 前端 Vue 项目
│   └── src/
│       ├── views/           # 页面
│       ├── api/             # 接口
│       ├── router/          # 路由
│       ├── store/           # 状态管理
│       └── components/      # 组件
└── .run/                   # IDEA 运行配置
```

## 快速开始

### 环境要求

- JDK 17+
- Node.js 18+
- Maven 3.8+
- Oracle 数据库

### 数据库配置

编辑 `tmzm-gmj-server/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:oracle:thin:@172.16.127.132:1521:HGM0
    username: hgm0
    password: hgm0
```

### 启动后端

```bash
cd tmzm-gmj-server
mvn spring-boot:run
```

或在 IDEA 中使用 `.run/后端启动` 配置。

### 启动前端

```bash
cd tmzm-gmj-ui
npm install
npm run dev
```

或在 IDEA 中使用 `.run/前端启动` 配置。

### 同时启动

在 IDEA 中使用 `.run/前后端同时启动` 配置。

### 默认账号

| 账号 | 密码 |
|------|------|
| admin | admin123 |
| hgm0 | hgm0 |

## 功能模块

### 计划管理
- 计划主表 (Ttmzm20A1)
- 计划明细 (Ttmzm20A2)
- 计划配置 (Ttmzm20Cfg)

### 设备管理
- 机架 (Frame)
- 鼓形件 (Drum)
- 冲头辊 (Punchroll)
- 轧辊 (Rolling)

## API 文档

启动后端后访问：http://localhost:8080/swagger-ui.html

## 贡献者

- [汤庄](https://github.com/Mman173)

## 许可证

MIT License
