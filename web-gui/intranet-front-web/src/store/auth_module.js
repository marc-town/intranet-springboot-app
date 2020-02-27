const auth = {
  state: {
    login: {
      token: false,
      name: "ゲスト",
      expire: 0
    }
  },
  mutations: {
    SET_LOGIN_INFO: (state, login) => {
      state.login.token  = login.token                     // ログイントークン
      state.login.name   = login.name                      // ユーザー名
      state.login.expire = Math.floor(1000 * login.expire) // APIからUNIXタイム(秒)で有効期限が返ってくるものとし、ミリ秒に変換しておく
    }
  },
  actions: {
    setLoginInfo({ commit }, login) {
      commit("SET_LOGIN_INFO", login)
    }
  }
}

export default auth