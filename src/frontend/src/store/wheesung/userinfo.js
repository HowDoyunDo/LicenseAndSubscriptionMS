export default {
  namespaced: true,

  state: {
    userInfo: {
      no : 1,
      email : 'kdy95@naver.com',
      name : '신혜란'
    }
  },
  mutations: {
    getUserInfo(state, payload) {
      state.userInfo = payload;
      console.log(state.userInfo);
    }
  },
  actions: {
  },
  modules: {
  }
}
