<template>
  <Form :model="formItem" :rules="ruleValidate" :label-width="80">
    <FormItem label="名称" prop="name">
      <Input v-model="formItem.name" placeholder="输入名称"></Input>
    </FormItem>
    <FormItem label="服务地址" prop="addr">
      <Input v-model="formItem.addr" placeholder="输入服务地址"></Input>
    </FormItem>
    <FormItem label="端口" prop="port">
      <InputNumber v-model="formItem.port" placeholder="端口" style="width:100%;"></InputNumber>
    </FormItem>
    <FormItem label="类型" prop="dbType">
      <Select v-model="formItem.dbType">
        <Option v-for="item in dbTypeList" :value="item.value" :key="item.value">{{ item.label }}</Option>
      </Select>
    </FormItem>
    <FormItem label="用户名" prop="username">
      <Input v-model="formItem.username" placeholder="请输入用户名"></Input>
    </FormItem>
    <FormItem label="密码" prop="password">
      <Input type="password" v-model="formItem.password" placeholder="请输入密码"></Input>
    </FormItem>
    <FormItem>
      <Button type="primary">确定</Button>
      <Button style="margin-left: 8px" to="/manage/connections">取消</Button>
    </FormItem>
  </Form>
</template>

<script>
    export default {
      name: "add",
      data() {
        return {
          dbTypeList: [
            {
              value: 'oracle',
              label: 'Oracle'
            },
            {
              value: 'mysql',
              label: 'MySQL'
            },
            {
              value: 'postgre',
              label: 'PostgreSQL'
            }
          ],
          formItem: {
            name: '',
            addr: '',
            port: 0,
            dbType: ''
          },
          ruleValidate: {
            name: [{
              required: true,
              message: '名称不能为空',
              trigger: 'blur'
            }],
            addr: [{
              required: true,
              message: '服务地址不能为空',
              trigger: 'blur'
            }],
            port: [{
              required: true,
              message: "端口号不能为空",
              trigger: "blur"
            }, {
              type: "integer",
              message: "端口号必须为整数",
              trigger: "blur"
            }],
            dbType: [{
              required: true,
              message: "类型不能为空",
              trigger: "blur"
            }],
            username: [{
              required: true,
              message: "请输入用户名",
              trigger: "blur"
            }],
            password: [{
              required: true,
              message: "请输入密码",
              trigger: "blur"
            }]
          }
        }
      },
      created() {
        console.log(this.$route.params.id);
      }
    }
</script>

<style scoped>

</style>
