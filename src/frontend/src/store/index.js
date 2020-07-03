import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

import productStore from '@/store/doyun/productStore.js'
import licenseStore from '@/store/doyun/licenseStore.js'
import userinfo from '@/store/wheesung/userinfo.js'

export default new Vuex.Store({
  modules: {
    productStore : productStore,
    licenseStore : licenseStore,
    userinfo : userinfo
  },
  plugins: [
    createPersistedState()
  ]
})
