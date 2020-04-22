# Todo-list_Team5

Todo List Frontend Work

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