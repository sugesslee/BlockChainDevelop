import Vue from 'vue';
import App from './App';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueParticles from 'vue-particles';
import i18n from './lang';
import echarts from 'echarts'
import './assets/js/fontSize'

Vue.prototype.$echarts = echarts

// mock数据
if (process.env.ENV_CONFIG==='dev') {
  require('./mock')
}


Vue.use(VueParticles);
Vue.use(ElementUI, {
  size: 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
});

Vue.config.productionTip = false;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
});
