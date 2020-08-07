import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex)
import subscribeStore from '@/store/shr/subscribeStore'
import promotionStore from '@/store/shr/promotionStore'
import productStore from '@/store/doyun/productStore.js'
import productStore_shr from '@/store/shr/productStore'
import licenseStore from '@/store/doyun/licenseStore.js'
import menuStore from '@/store/doyun/menuStore.js'
import userinfo from '@/store/wheesung/userinfo.js'

export default new Vuex.Store({
  modules: {
    productStore : productStore,
    licenseStore : licenseStore,
    userinfo : userinfo,
    subscribeStore: subscribeStore,
    productStore_shr: productStore_shr,
    promotionStore : promotionStore,
    menuStore : menuStore
  },
  plugins: [
    createPersistedState()
  ]
})
