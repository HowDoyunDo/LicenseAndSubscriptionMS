import Vue from 'vue'
import Vuex from 'vuex'

import subscribeStore from '@/store/shr/subscribeStore'

Vue.use(Vuex)

export default new Vuex.Store({
  namespaced: true,
  modules: {
    subscribeStore: subscribeStore,
  }
})
