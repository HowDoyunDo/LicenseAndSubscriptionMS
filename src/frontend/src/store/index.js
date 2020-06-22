import Vue from 'vue'
import Vuex from 'vuex'
import { productOneList } from "@/api/shr/product";

// import porudctModule from './shr/product'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    // counterr: 100,
    productList: [],
  },
  mutations: {
    // commit , setters 로 이해
    // addCount: function (state, payload) {
    //   return state.counterr++;
    // },

  },
  actions: {
    //dispatch, 비동기 , 서버 ... 
    // addCount: function (context, payload) {
    //   return context.commit('addCount');
    // },

    async selectOneProduct(context, payload) {
      const { data } = await productOneList({ no: payload });
      this.state.productList.push(data[0]);
    },
    deleteOneProduct(context, payload) {
      this.state.productList.splice(payload, 1);
    }
  },
  modules: {
    // shrStore : porudctModule,
  },
  getters: {
    // getCounter: function (state) {
    //   return state.counterr;
    // },

    getProductList(state){
      return state.productList;
    },
  }
})
