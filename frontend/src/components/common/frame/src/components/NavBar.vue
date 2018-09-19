<template>
  <div class="navbar">
      <div class="content">
        <div class="logo"></div>
        <div class="logo-text">商城管理</div>
        <ul class="nav">
          <li>首页</li>
          <li>我的账户</li>
          <li>注销</li>
        </ul>
      </div>
  </div>
</template>

<script>
    export default {
        name: "nav-bar"
    }
</script>

<style lang="less" scoped>
  @import "styles/variable.less";
  .navbar{
      background-color:rgb(102,51,0 );
      .content{
         width:1280px;
         margin:0 auto;
         overflow:hidden;
         .logo{
            width:120px;
            height:60px;
            background-size:100%;
            background-image:url("../style/image/logo.png");
            float:left;
            margin-left:-20px;
         }
         .logo-text{
            color:rgba(255,255,255,0.7);
            float:left;
            font-size:20px;
            margin-top:15px;

         }
         .nav{
            float:left;
            overflow:hidden;
            li{
              float:left;
              color:#fff;
              font-size:16px;
              margin-top:20px;
              margin-left:50px;
            }
         }

      }
  }
</style>
