<template>
  <div class="login" @keydown.enter="handleSubmit">
    <div class="login-box">
      <div class="logo">雪茄烟订货平台</div>
      <div class="main-content">
        <div class='login-pic'></div>
        <div class="login-content">
          <div class="login-data">
            <Form ref="loginForm" :model="form" :rules="rules">
              <FormItem prop="userName" label="用户名">
                <Input v-model="form.userName" placeholder="请输入用户名" suffix="md-person" />
              </FormItem>
              <FormItem prop="password" label="密码">
                <Input type="password" v-model="form.password" placeholder="请输入密码" suffix="md-lock" />
              </FormItem>
              <FormItem style="margin-top:40px;">
                <Button @click="handleSubmit" type="primary" long>登录</Button>
              </FormItem>
            </Form>
          </div>
        </div>
      </div>

      <div class="main-shadow"></div>

      <div class="foot">
        <div class="em">China NationalTobacco Corporation All Right Reserved</div>
        <div class="em">中国烟草总公司 &nbsp; 版权所有</div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props:{
      defaultUserName:String,
      defaultPassword:String
    },
    data() {
      return {
        form: {
          userName: this.defaultUserName,
          password: this.defaultPassword
        },
        rules: {
          userName: [
            {
              validator: function(rule, value, callback){
                if(!value){
                  return callback(new Error('用户名不能为空'));
                }else{
                  return callback();
                }
              },
              trigger: 'blur'
            }
          ],
          password: [
            {
              validator: function(rule, value, callback){
                if(!value){
                  return callback(new Error('密码不能为空'));
                }else{
                  return callback();
                }
              },
              trigger: 'blur'
            }
          ]
        }
      };
    },
    methods: {
      handleSubmit() {
        var self = this;
        self.$refs.loginForm.validate((valid) => {
          if(valid) {
            self.doLogin(self.form.userName, self.form.password, "false");
          }
        });
      },
      doLogin(username, password, rememberMe) {
        this.$emit("do-login", {
          username,
          password,
          rememberMe
        });
      }
    }
  };
</script>

<style lang="less" scoped>
  .login {
    width: 100%;
    height: 100%;
    .login-box{
      width:980px;
      height:530px;
      margin:0 auto;
      padding-top:3.83%;
      .logo{
        height:72px;
        background:url('../styles/logo.jpg') no-repeat;
        margin-bottom: 32px;
        color:#000;
        font-size:22px;
        font-weight: bold;
        letter-spacing: 1px;
        text-indent:245px;
        line-height:72px;
      }
      .main-content{
        width: 980px;
        height:304px;
        padding:7px;
        background-color: rgb(249, 249, 249);
        .login-pic{
          background:url('../styles/xjysc-bg.jpg');
          width:608px;
          height:290px;
          float: left;
        }
        .login-content {
          height: 290px;
          width: 280px;
          float: right;
          margin-right: 40px;
          .login-data {
            position: relative;
            top:20px;
          }
        }
      }
      .main-shadow{
        width:980px;
        height:15px;
        background:url('../styles/logb.png');
        margin-left:auto;
        margin-right:auto;
      }
      .foot{
        border-top: 1px dotted #808080;
        text-align: center;
        margin-top: 40px;
        padding-top: 14px;
        .em{
          font-size: 12px;
          color: #808080;
          line-height: 18px;
          letter-spacing: 1px;
        }
      }
    }
  }
</style>
