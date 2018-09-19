import Vue from 'vue'
import Router from 'vue-router'
import connection from '@/views/connection/list'
import addConnection from '@/views/connection/add'
import login from '@/views/login/login'
import home from '@/components/home/index'
import main from '@/views/main/main'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录页',
      component: login,
    },{
      path: "/home",
      name: "主页面",
      component: main,
      children: [{
        path: 'manage/connections/edit/:id',
        name: '编辑连接',
        component: addConnection
      }, {
        path: 'manage/connections',
        name: '我的连接',
        component: connection
      }, {
        path: 'manage/share/connections',
        name: '共享连接',
        component: connection
      }, {
        path: 'manage/connections/add',
        name: '新增连接',
        component: addConnection
      }, {
        path: 'manage/views',
        name: "数据视图",
        component: () => import('@/views/dataview/list.vue')
      }, {
        path: 'manage/boards',
        name: "仪表盘",
        component: () => import('@/views/board/list.vue')
      }, {
        path: "manage/widgets",
        name: "图表组件",
        component: () => import('@/views/widget/list.vue')
      }]
    }, {
      path: "/bsp/pwdRule",
      name: "密码设置页面",
      component: () => import('@/views/bsp/globalpolicy/pwdRule.vue')
    }, {
      path: "/dict/list",
      name: "字典列表",
      component: () => import('@/views/public/dict/list.vue')
    }, {
      path: "/dict/items",
      name: "字典项",
      component: () => import('@/views/public/dict/item.vue')
    }
  ]
})
