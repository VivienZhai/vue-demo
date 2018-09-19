<template>
  <div class="login" @keydown.enter="handleLogin">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
  import loginForm from '@/components/login-form/login-form';
  import home from '@/components/home/index'
  import util from '@/libs/util'
  export default {
    name: "login",
    components: {
      loginForm
    },
    methods: {
      handleSubmit({username, password}) {
        var self = this;
        util.ajax.post('/viz/login', {
          username: username,
          password: password
        }).then(res => {
          console.log(res.data);
          let data = res.data;
          if(data.code == 200) {
            self.$router.push({path: '/home'})
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
