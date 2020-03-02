const staff = {
  namespaced: true,
  state: {
    staffId: 0,
    registDialog: false,
    deleteDialog: false,
  },
  mutations: {
    SET_STAFF_ID: (state, value) => {
      state.staffId = value
    },
    SET_REGIST_DIALOG: (state, value) => {
      state.registDialog = value
    },
    SET_DELETE_DIALOG: (state, value) => {
      state.deleteDialog = value
    },
  },
  actions: {
    setStaffId({ commit }, value) {
      commit("SET_STAFF_ID", value)
    },
    setRegistDialog({ commit }, value) {
      commit("SET_REGIST_DIALOG", value)
    },
    setDeleteDialog({ commit }, value) {
      commit("SET_DELETE_DIALOG", value)
    },
  }
}

export default staff