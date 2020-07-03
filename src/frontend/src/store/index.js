import Vue from 'vue'
import Vuex from 'vuex'

import subscribeStore from '@/store/shr/subscribeStore'
import productStore from '@/store/shr/productStore'
import promotionStore from '@/store/shr/promotionStore'
import userinfo from '@/store/wheesung/userinfo.js'

Vue.use(Vuex)

export default new Vuex.Store({
  namespaced: true,
  modules: {
    subscribeStore: subscribeStore,
    productStore: productStore,
    promotionStore : promotionStore,
    userinfo : userinfo
  }
})
