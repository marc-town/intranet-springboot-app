const error = {
  namespaced: true,
  state: {
    errorDialog: false,
    status: 0,
    code: '',
    message: '',
  },
  mutations: {
    SET_ERROR_DIALOG: (state, value) => {
      state.errorDialog = value
      state.status = 400
    }
  },
  actions: {
    setErrorDialog: ({ commit }, value) => {
      commit("SET_ERROR_DIALOG", value)
    }
  }
}

export default error