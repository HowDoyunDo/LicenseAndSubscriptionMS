export default {
  namespaced: true,

  state: {
    userInfo: "",
    categoryInfo: "",
    categoryList : '',
  },
  getter:{

  },
  mutations: {
    setUserInfo(state, payload) {
      state.userInfo = payload;
    },
    setCategoryInfo(state, payload){
      state.categoryInfo = payload;
    },
    setCategoryList(state, payload){
      console.log('스토어 ' , payload)
      state.categoryList = payload;
    }
  },
  actions: {
  },
}