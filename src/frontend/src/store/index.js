import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)

import productStore from '@/store/doyun/productStore.js'
import licenseStore from '@/store/doyun/licenseStore.js'
import userinfo from '@/store/wheesung/userinfo.js'
import subscribeStore from '@/store/shr/subscribeStore'
import productStore_shr from '@/store/shr/productStore'
import promotionStore from '@/store/shr/promotionStore'

export default new Vuex.Store({
  namespaced: true,
  modules: {
    productStore: productStore,
    licenseStore: licenseStore,
    userinfo: userinfo,
    subscribeStore: subscribeStore,
    productStore_shr: productStore_shr,
    promotionStore : promotionStore,
  },
  plugins: [
    createPersistedState()
  ]
})
