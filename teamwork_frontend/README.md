Todo List Frontend Work

## 启动
`npm install`

`npm start`

## 运行App.test.js 
#### 加载mock相关包

1.install mockjs

`npm install mockjs --save-dev`

2.install fetch-mock

`npm install i -D fetch-mock`

3.install node-fetch

`npm install node-fetch -save`

#### run test

`npm run test`

## 与后端连接
1.注释 `api/BaseApi.js` 的第一行

`// import '../mock/mock'`

2.在 `package.json` 中加入后端的端口号

`"proxy": "http://127.0.0.1:8080"`