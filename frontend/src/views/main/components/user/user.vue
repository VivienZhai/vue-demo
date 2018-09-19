<template>
  <div class="user-avator-dropdown">
    <Dropdown @on-click="handleClick">
      <Avatar icon="ios-person"/>
      <Icon :size="12" type="arrow-down-b"></Icon>
      <DropdownMenu slot="list">
        <DropdownItem name="logout">退出登录</DropdownItem>
      </DropdownMenu>
    </Dropdown>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  export default {
    name: 'user',
    methods: {
      ...mapActions([
        'handleLogOut'
      ]),
      handleClick (name) {
        switch (name) {
          case 'logout':
            console.log('退出');
            break;
        }
      }
    }
  }
</script>

<style lang="less">
  .user{
  &-avator-dropdown{
     display: inline-block;
     height: 32px;
     vertical-align: middle;
     line-height: 10px;
   }
  }
</style>
