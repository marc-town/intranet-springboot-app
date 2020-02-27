const auth = {
  state: {
    login: {
      token: false,
      staffId: 0,
      loginId: '',
      role: ''
    }
  },
  mutations: {
    SET_LOGIN_INFO: (state, auth) => {
      state.login.token   = auth.token   // JWT
      state.login.staffId = auth.staffId // 社員ID
      state.login.loginId = auth.loginId // ログインID
    }
  },
  actions: {
    setLoginInfo({ commit }, auth) {
      commit("SET_LOGIN_INFO", auth)
    }
  }
}

export default auth