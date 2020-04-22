# Todo-list_Team5
## 功能

- 返回所有Todo任务
- 创建一个新的Todo任务
- 返回一个指定ID的Todo任务
- 删除一个Todo任务
- 数据存储使用简单的文件存储

## 代码结构
    Todo-list
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

##Todo List Backend Work
###启动
1.更新数据文件的路径：teamwork_backend/src/main/resources/application.properties

2.安装依赖

`npm install`

3.运行

`./gradlew bootRun` or `./gradlew.bat bootRun`

##Todo List Frontend Work

### 启动
加载mock及其他相关包

1.install npm 

`npm install`

2.install mockjs

`npm install mockjs --save-dev`

3.install fetch-mock

`npm install i -D fetch-mock`


运行

`npm start`

### 测试
运行 

1.install node-fetch

`npm install node-fetch -save`

2.run test

`npm run test`


### 与后端连接
1.注释 `api/BaseApi.js` 的第一行

`// import '../mock/mock'`

2.在 `package.json` 中加入后端的端口号

`"proxy": "http://127.0.0.1:8080"`


##Todo List e2e Test

###测试

1.安装依赖

`npm install`

2.运行测试

`npm run test`