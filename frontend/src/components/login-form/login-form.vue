<template>
  <Form ref="loginForm" :model="form" :rules="rules">
    <FormItem prop="username">
      <Input v-model="form.username" placeholder="请输入用户名" prefix="md-person" />
    </FormItem>
    <FormItem prop="password">
      <Input type="password" v-model="form.password" placeholder="请输入密码" prefix="md-lock" />
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" long>登录</Button>
    </FormItem>
  </Form>
</template>
<script>
  export default {
    name: 'loginForm',
    props: {
      usernameRules: {
        type: Array,
        default: () => {
          return [
            { required: true, message: '账号不能为空', trigger: 'blur' }
          ]
        }
      },
      passwordRules: {
        type: Array,
        default: () => {
          return [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    data () {
      return {
        form: {
          username: 'superadmin',
          password: ''
        }
      }
    },
    computed: {
      rules () {
        return {
          username: this.usernameRules,
          password: this.passwordRules
        }
      }
    },
    methods: {
      handleSubmit () {
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.$emit('on-success-valid', {
              username: this.form.username,
              password: this.form.password
            })
          }
        })
      }
    }
  }
</script>
<style lang="less">
  .login{
    width: 100%;
    height: 100%;
    background-image: url('../../assets/login_bg.jpg');
    background-size: cover;
    background-position: center;
    position: relative;
    &-con{
      position: absolute;
      right: 160px;
      top: 50%;
      transform: translateY(-60%);
      width: 300px;
      &-header{
        font-size: 16px;
        font-weight: 300;
        text-align: center;
        padding: 30px 0;
      }
      .form-con{
        padding: 10px 0 0;
      }
      .login-tip{
        font-size: 10px;
        text-align: center;
        color: #c3c3c3;
      }
    }
  }
</style>
