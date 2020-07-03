export default {
    namespaced: true,
  
    state: {
      userInfo: "",
    },
    getter:{
  
    },
    mutations: {
      setUserInfo(state, payload) {
        state.userInfo = payload;
      }
    },
    actions: {
    },
  }