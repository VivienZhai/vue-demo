<template>
  <Layout style="height: 100%" class="main">
    <Sider hide-trigger collapsible :width="210" :collapsed-width="64" v-model="collapsed">
      <side-menu accordion :active-name="$route.name" :collapsed="collapsed" @on-select="turnToPage" :menu-list="menuList">
        <!-- 需要放在菜单上面的内容，如Logo，写在side-menu标签内部，如下 -->
        <div class="logo-con">
          <img v-show="!collapsed" :src="maxLogo" key="max-logo" />
          <img v-show="collapsed" :src="minLogo" key="min-logo" />
        </div>
      </side-menu>
    </Sider>
    <Layout>
      <Header class="header-con">
        <header-bar :collapsed="collapsed" @on-coll-change="handleCollapsedChange">
          <user/>
        </header-bar>
      </Header>
      <Content class="main-content">
        <keep-alive>
          <router-view/>
        </keep-alive>
      </Content>
    </Layout>
  </Layout>
</template>
<script>
  import sideMenu from './components/side-menu/side-menu'
  import headerBar from './components/header-bar/header-bar'
  import user from './components/user/user'
  import { mapMutations, mapActions } from 'vuex'
  import minLogo from '@/assets/images/logo-min.jpg'
  import maxLogo from '@/assets/images/logo.jpg'

  export default {
    name: 'Main',
    components: {
      sideMenu,
      headerBar,
      user
    },
    data () {
      return {
        collapsed: false,
        minLogo,
        maxLogo,
        menuList: [{
          icon: 'logo-buffer',
          name: "数据连接",
          children: [
            {
              icon: "logo-buffer",
              name: "我的连接"
            }, {
              icon: "logo-buffer",
              name: "共享连接"
            }
          ]
        }, {
          icon: 'md-cube',
          name: "数据视图",
          children: [
            {
              icon: 'md-cube',
              name: '我的视图'
            }, {
              icon: 'md-cube',
              name: "共享视图"
            }
          ]
        }, {
          icon: 'md-stats',
          name: "图表组件",
          children: [{
            icon: 'md-stats',
            name: '我的图表'
          }, {
            icon: 'md-stats',
            name: '共享图表'
          }]
        }, {
          icon: 'ios-speedometer',
          name: "仪表盘",
          children: [{
            icon: 'ios-speedometer',
            name: '我的仪表盘'
          }, {
            icon: 'ios-speedometer',
            name: '共享仪表盘'
          }]
        }]
      }
    },
    computed: {

    },
    methods: {
      ...mapMutations([
        'setBreadCrumb',
        'setTagNavList',
        'addTag'
      ]),
      ...mapActions([
        'handleLogin'
      ]),
      turnToPage (name) {
        this.$router.push({
          name: name
        })
      },
      handleCollapsedChange (state) {
        this.collapsed = state
      },
      handleClick (item) {
        this.turnToPage(item.name)
      }
    },
    watch: {
      '$route' (newRoute) {
      }
    },
    mounted () {
      /**
       * @description 初始化设置面包屑导航和标签导航
       */
    }
  }
</script>
<style lang="less">
  .main{
    .logo-con{
      height: 64px;
      padding: 10px;
      img{
        height: 44px;
        width: auto;
        display: block;
        margin: 0 auto;
      }
    }
    .header-con{
      background: #fff;
      padding: 0 20px;
    }

    .main-content {
      padding: 15px;
    }
    .tag-nav-wrapper{
      padding: 0;
      height:40px;
      background:#F0F0F0;
      overflow: hidden;
    }
  }
</style>
