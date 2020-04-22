# Todo-list_Team9
## 功能

- 返回所有Todo任务
- 创建一个新的Todo任务
- 返回一个指定ID的Todo任务
- 删除一个Todo任务
- 数据存储使用简单的文件存储

## 代码结构
    Todo-list_Team9
       |
       |-- teamwork_backend  // 使用SpringBoot构建RESTful API
       |
       |-- teamwork_frontend  // 使用ReactJs构建前端组件
       |
       |-- e2e // 使用Puppeteer进行页面控制，实现端到端测试
       |
       |-- data // 用来存放Task数据

## Task文件格式

    {
      "id": 1,
      "content": "Restful API homework",
      "updatedTime": "2019-05-15 00:00:00"
    }

## Todo List Backend Work
### 进入后端文件夹

`cd teamwork_backend`

### 启动
1.更新数据文件的路径：teamwork_backend/src/main/resources/application.properties


2.运行

`./gradlew bootRun` or `./gradlew.bat bootRun`

## Todo List Frontend Work

### 进入前端文件夹

`cd teamwork_frontend`

### 启动

1.加载相关包

`npm install`

2.运行

`npm start`

### 进入e2e文件夹

`cd e2e`

### 测试

1.安装依赖

`npm install`

2.运行测试

`npm run test`