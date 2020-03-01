const common = {
  namespaced: true,
  state: {
    drawer: true,
  },
  getters: {
    drawer(state) {
      return state.drawer
    }
  },
  mutations: {
    SET_DRAWER(state, value) {
      state.drawer = value
    }
  },
  actions: {
    setDraewer({ commit }, value) {
      commit("SET_DRAWER", value)
    }
  }
}

export default common