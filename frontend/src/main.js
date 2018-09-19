// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import Vuex from 'vuex';
import iView from 'iview';
import App from './App';
import router from './router';
import '@/styles/index.less';

Vue.use(iView, {
  transfer: true
});
Vue.use(Vuex);

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
});

