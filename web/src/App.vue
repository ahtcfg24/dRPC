<template>
  <div id="app">
    <img src="./assets/logo.png">
    <h1>dRPC Dashboard</h1>
    <el-button type="primary" v-on:click="submitStudent">发起请求</el-button>
    <el-button type="primary" v-on:click="reset">重置</el-button>
    <br><br>
    <el-table
      v-loading="loading"
      :data="requestHistory"
      border=true>
      <el-table-column
        type="index"
        :index="indexMethod">
      </el-table-column>
      <el-table-column
        align=center
        prop="requestTime"
        label="请求时间">
      </el-table-column>
      <el-table-column
        align=center
        prop="server"
        label="服务器内网IP地址">
      </el-table-column>
      <el-table-column
        align=center
        prop="timeMills"
        label="RPC耗时">
      </el-table-column>
      <el-table-column
        align=center
        prop="requestState"
        label="请求状态">
      </el-table-column>
      <el-table-column
        align=center
        prop="responseData"
        label="服务器回应">
      </el-table-column>

    </el-table>

  </div>
</template>

<script>

  const address = "http://api.iamding.cn/drpc";
  const url = "/test/submit/student";
  const requestBody = {"photo": "this is photo", "sex": "man", "name": "AmonTin", "age": 21, "rpc": true};
  const requestHeaders = {"Content-Type": "application/json"};

  export default {
    name: "app",
    data() {
      return {
        loading: false,
        requestHistory: []

      };
    },
    methods: {
      submitStudent: function () {
        this.loading = true;
        let _this = this;
        this.$http.post(address + url, requestBody, {headers: requestHeaders})
          .then(function (resp) { //success
            _this.loading = false;
            let respData = resp.data;
            let lineData = {
              requestTime: dateFtt("yyyy-MM-dd hh:mm:ss", new Date()),
              server: respData.server,
              timeMills: respData.timeMills + "ms",
              requestState: respData.code,
              responseData: JSON.stringify(respData.data)
            };
            if (lineData.requestState === 0) {
              lineData.requestState = "请求成功"
            } else {
              lineData.requestState = "请求失败"
            }

            _this.requestHistory.push(lineData);
            console.log("succ:" + respData);
          }, function (resp) { //failed
            _this.loading = false;
            console.log("error:" + resp.data);
          });


      },
      reset: function () {
        this.requestHistory = [];
        this.loading = false;

      }
      , indexMethod(index) {
        return index + 1;
      },
    }
  };

  function dateFtt(fmt, date) {
    let o = {
      "M+": date.getMonth() + 1,                 //月份
      "d+": date.getDate(),                    //日
      "h+": date.getHours(),                   //小时
      "m+": date.getMinutes(),                 //分
      "s+": date.getSeconds(),                 //秒
      "q+": Math.floor((date.getMonth() + 3) / 3), //季度
      "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
      if (new RegExp("(" + k + ")").test(fmt))
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
  }
</script>

<style>
  #app {
    font-family: "Avenir", Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }


</style>
