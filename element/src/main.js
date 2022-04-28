import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import '@/assets/icon/iconfont.css'

Vue.config.productionTip = false

import axios  from 'axios'
Vue.prototype.$http = axios.create({
  baseURL: 'http://localhost:8082/'
})

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
