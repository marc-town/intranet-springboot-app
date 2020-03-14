const error = {
  namespaced: true,
  state: {
    errorDialog: false,
    status: 0,
    code: '',
    message: '',
  },
  mutations: {
    SET_ERROR_DIALOG: (state, error) => {
      state.errorDialog = error.errorDialog
      state.status = error.status
      state.code = error.code
      state.message = error.message
    }
  },
  actions: {
    setErrorDialog: ({ commit }, error) => {
      commit("SET_ERROR_DIALOG", error)
    }
  }
}

export default error