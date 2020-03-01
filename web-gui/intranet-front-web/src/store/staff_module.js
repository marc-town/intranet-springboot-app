const staff = {
  namespaced: true,
  state: {
    dialog: false,
  },
  mutations: {
    SET_DIALOG: (state, value) => {
      state.dialog = value
    },
  },
  actions: {
    setDialog({ commit }, value) {
      commit("SET_DIALOG", value)
    },
  }
}

export default staff