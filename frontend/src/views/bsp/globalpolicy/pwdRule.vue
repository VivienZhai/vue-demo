<template>
  <Form ref="formItem" :model="formItem" :rules="ruleValidate" :label-width="200">
    <Card :dis-hover="true" :bordered="false">
      <p solt="title">密码策略</p>
      <FormItem label="密码最小长度" prop="pwdMinLength">
        <InputNumber v-model="formItem.pwdMinLength" :min="6" :max="30" placeholder="密码最小长度"></InputNumber>
      </FormItem>
      <FormItem label="密码最大长度" prop="pwdMaxLength">
        <InputNumber v-model="formItem.pwdMaxLength" :min="6" :max="30" placeholder="密码最大长度"></InputNumber>
      </FormItem>
      <FormItem label="包含大写字母">
        <i-switch v-model="formItem.isUpCase">
          <span slot="open">开</span>
          <span slot="close">关</span>
        </i-switch>
      </FormItem>
      <FormItem label="包含大写字母">
        <i-switch v-model="formItem.isLowerCase">
          <span slot="open">开</span>
          <span slot="close">关</span>
        </i-switch>
      </FormItem>
      <FormItem label="包含特殊符号">
        <i-switch v-model="formItem.isSpecialChar">
          <span slot="open">开</span>
          <span slot="close">关</span>
        </i-switch>
      </FormItem>
    </Card>
    <Card :dis-hover="true" :bordered="false">
      <p solt="title">锁定策略</p>
      <FormItem label="登录失败次数" prop="loginAttempts">
        <InputNumber v-model="formItem.loginAttempts"></InputNumber>
        <span style="margin-left: 10px;">(帐户锁定的登录失败次数)</span>
      </FormItem>
      <FormItem label="用户锁定时间" prop="pwdLockTime">
        <InputNumber v-model="formItem.pwdLockTime"></InputNumber>
        <span style="margin-left: 10px;">(分钟, 负数表永久; 帐户密码输入错误后被锁定的时间)</span>
      </FormItem>
      <FormItem label="密码有效时间" prop="pwdLifeTime">
        <InputNumber v-model="formItem.pwdLifeTime"></InputNumber>
        <span style="margin-left: 10px;">(分钟, 负数表永久; 密码需要修改的提示时间)</span>
      </FormItem>
      <FormItem label="密码失效前更改的时间" prop="pwdGraceTime">
        <InputNumber v-model="formItem.pwdGraceTime"></InputNumber>
        <span style="margin-left: 10px;">(分钟, 负数表永久; 密码有效期后到密码失效用户锁定的时间)</span>
      </FormItem>
      <FormItem>
        <Button type="primary">保存</Button>
        <Button @click="handleReset('formItem')" style="margin-left: 8px">重置</Button>
      </FormItem>
    </Card>
  </Form>
</template>

<script>
    export default {
      name: "pwdRule",
      data() {
        return {
          formItem: {
            pwdMinLength: 6,
            pwdMaxLength: 30,
            isUpCase: false,
            isLowerCase: false,
            isNum: false,
            isSpecialChar: false,
            loginAttempts: 3,
            pwdLockTime: 0,
            pwdLifeTime: 0,
            pwdGraceTime: 0
          },
          ruleValidate: {
            pwdMinLength: [{
              required: true,
              trigger: 'blur'
            }],
            pwdMaxLength: [{
              required: true,
              trigger: 'blur'
            }],
            loginAttempts:[{
              required: true,
              trigger: 'blur'
            }],
            pwdLockTime: [{
              required: true,
              trigger: 'blur'
            }],
            pwdLifeTime: [{
              required: true,
              trigger: 'blur'
            }],
            pwdGraceTime: [{
              required: true,
              trigger: 'blur'
            }]
          }
        }
      },
      methods: {
        handleSubmit() {
          // TODO 执行ajax提交
        },
        handleReset (name) {
          this.$refs[name].resetFields();
        }
      }
    }
</script>

<style scoped>

</style>
