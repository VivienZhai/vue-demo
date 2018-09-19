<template>
  <div class="frame left-menu">
    <Menu ref="sideMenu" width="180px" @on-select="changeMenu" @on-open-change="openChange" :active-name = "activeName">
      <sidebarSubMenu :menuList="menuList"></sidebarSubMenu>
    </Menu>
  </div>

</template>

<script>
  import sidebarSubMenu from './sidebarSubMenu.vue';

  export default {
    data() {
      return {
        openedSubmenuArr: [],
        currentPageName: 0,
        hasOpenChild: 0,
        oldCurrentPageName: '',
        activeName:"",
      };
    },
    name: 'sidebarMenu',
    props: {
      slotTopClass: String,
      menuList: Array,
      menuTheme: String,
      iconSize: Number,
    },
    components: {
      sidebarSubMenu
    },
    computed: {
      userId() {
        return "";
      },
    },
    methods: {
      changeMenu(active) {
        this.$router.push({
          name: active
        });
      },
      openChange(names) {
        console.log(names);
      },
      initData(){
        let menuList = JSON.parse(sessionStorage.tagsList) || [];
        for(let menu of menuList){
          if(menu.name === this.$route.name){
            this.activeName = this.$route.name;
            break;
          }
        }
      }
    },
    updated() {
      this.$nextTick(function(){
        this.$refs.sideMenu.updateActiveName();
      });
    },
    created() {
      this.initData();
    },
    watch: {
      '$route' (to,from) {
        this.initData();
      },
      menuList(data){
        this.initData();
      }
    },

  };
</script>
<style lang="less">
  .frame.left-menu {
    padding-bottom: 15px;
    background: #ffffff;
    .ivu-menu-item-group{
      .ivu-menu-item-group-title{
        padding-left: 15px;
        color:#000000;
        font-weight: bolder;
        font-size: 16px;
      }
      .ivu-menu-item{
        padding: 5px 15px;
        color: #999;
        &.ivu-menu-item-selected{
          color:#c90;
        }
        &.ivu-menu-item-active{
          color:#c90;
        }
        &:hover{
          color:#c90;
        }
      }
    }
    .ivu-menu-light {
      &:after {
        content: '';
        display: none;
      }
      .ivu-menu-item-active {
        background: none;
        &:after {
          content: '';
          display: none;
          width: 2px;
          position: absolute;
          top: 0;
          bottom: 0;
          left: 0;
        }
      }
      .ivu-menu-submenu-title {
        font-weight: bold;
      }
    }
  }

</style>
