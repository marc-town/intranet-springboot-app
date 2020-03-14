const getDefaultState = () => {
  return {
    snackbar: false,
    color: 'info',
    message: '',
    timeout: 5000,
    x: null,
    y: 'top'
  }
}
const snackbar = {
  namespaced: true,
  state: getDefaultState,
  mutations: {
    SET_SNACKBAR: (state, options) => {
      state.snackbar = options.snackbar
      state.color = options.color
      state.message = options.message
      state.timeout = options.timeout
    },
    RESET_SNACKBAR: state => {
      Object.assign(state, getDefaultState());
    }
  },
  actions: {
    setSnackbar: ({ commit }, options) => {
      commit("SET_SNACKBAR", options)
    },
    resetSnackbar: ({ commit }) => {
      commit('RESET_SNACKBAR');
    }
  }
}

export default snackbar