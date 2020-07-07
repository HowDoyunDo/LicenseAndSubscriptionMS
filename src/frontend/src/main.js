import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as filters from './assets/filters/filters';

Object.keys(filters).forEach(function(key) {
  Vue.filter(key, filters[key]);
});



Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
