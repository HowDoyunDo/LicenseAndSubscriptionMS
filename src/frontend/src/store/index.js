import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)
import subscribeStore from '@/store/shr/subscribeStore'
import promotionStore from '@/store/shr/promotionStore'
import productStore from '@/store/doyun/productStore.js'
import productStore_shr from '@/store/shr/productStore'
import licenseStore from '@/store/doyun/licenseStore.js'
import userinfo from '@/store/wheesung/userinfo.js'

export default new Vuex.Store({
  modules: {
    subscribeStore: subscribeStore,
    productStore : productStore,
    productStore_shr: productStore_shr,
    licenseStore : licenseStore,
    promotionStore : promotionStore,
    userinfo : userinfo
  },
  plugins: [
    createPersistedState()
  ]
})
