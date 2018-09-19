<template>
  <div class="layout">
    <Layout>
      <Sider ref="siderbar" hide-trigger collapsible :collapsed-width="78" v-model="isCollapsed" class="siderbar">
        <div class="wrapper-header-nav">Logo</div>
        <Menu active-name="menu-connections" theme="dark" width="auto" :class="menuitemClasses">
          <MenuItem name="menu-connections" to="/manage/connections">
            <icon name="database"></icon>
            <span>数据连接</span>
          </MenuItem>
          <MenuItem name="menu-dataview">
            <Icon type="ios-navigate"></Icon>
            <span>数据视图</span>
          </MenuItem>
          <MenuItem name="menu-widget">
            <Icon type="ios-search"></Icon>
            <span>图表</span>
          </MenuItem>
          <MenuItem name="menu-board">
            <Icon type="ios-settings"></Icon>
            <span>仪表盘</span>
          </MenuItem>
        </Menu>
      </Sider>
      <Layout>
        <Header :style="{padding: 0}" class="layout-header-bar">
          <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu" size="24"></Icon>
          <Breadcrumb :style="{display: 'inline-block'}">
            <BreadcrumbItem to="/home">主页</BreadcrumbItem>
            <BreadcrumbItem>数据连接</BreadcrumbItem>
          </Breadcrumb>
        </Header>
        <Content :style="{margin: '20px', background: '#fff', 'min-height': '200px'}">
          <router-view/>
        </Content>
      </Layout>
    </Layout>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        isCollapsed: false
      }
    },
    computed: {
      rotateIcon () {
        return [
          'menu-icon',
          this.isCollapsed ? 'rotate-icon' : ''
        ];
      },
      menuitemClasses () {
        return [
          'menu-item',
          this.isCollapsed ? 'collapsed-menu' : ''
        ]
      }
    },
    methods: {
      collapsedSider () {
        this.$refs.siderbar.toggleCollapse();
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    position: absolute;
    top: 0px;
    left: 0px;
    right: 0px;
    bottom: 0px;
  }
  .layout-header-bar{
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  .layout-logo-left{
    width: 90%;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    margin: 15px auto;
  }

  .siderbar {
    height: 100%;
  }
  .wrapper-header-nav {
    height: 64px;
    color: #fff;
    font-size: 1.5em;
    line-height: 64px;
    text-align: center;
  }
  .menu-icon{
    transition: all .3s;
  }
  .rotate-icon{
    transform: rotate(-90deg);
  }
  .menu-item span{
    display: inline-block;
    overflow: hidden;
    width: 69px;
    text-overflow: ellipsis;
    white-space: nowrap;
    vertical-align: bottom;
    transition: width .2s ease .2s;
  }
  .menu-item i{
    transform: translateX(0px);
    transition: font-size .2s ease, transform .2s ease;
    vertical-align: middle;
    font-size: 16px;
  }
  .collapsed-menu span{
    width: 0px;
    transition: width .2s ease;
  }
  .collapsed-menu i{
    transform: translateX(5px);
    transition: font-size .2s ease .2s, transform .2s ease .2s;
    vertical-align: middle;
    font-size: 22px;
  }
</style>
