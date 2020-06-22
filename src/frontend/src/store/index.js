import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

import productStore from '@/store/doyun/productStore.js'

export default new Vuex.Store({
  modules: {
    productStore : productStore
  },
  plugins: [
    createPersistedState()
  ]
})
