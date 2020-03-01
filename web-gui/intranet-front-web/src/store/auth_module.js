const auth = {
  namespaced: true,
  state: {
    token: false,
    staffId: 0,
    loginId: '',
    role: ''
  },
  mutations: {
    SET_LOGIN_INFO: (state, auth) => {
      state.token   = auth.token   // JWT
      state.staffId = auth.staffId // 社員ID
      state.loginId = auth.loginId // ログインID
      state.role    = auth.role    // 権限
    }
  },
  actions: {
    setLoginInfo({ commit }, auth) {
      commit("SET_LOGIN_INFO", auth)
    }
  }
}

export default auth