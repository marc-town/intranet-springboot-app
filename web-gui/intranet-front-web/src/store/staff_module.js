const staff = {
  namespaced: true,
  state: {
    selectedStaffId: 0,
    selectedStaffName: '',
    registDialog: false,
    deleteDialog: false,
  },
  mutations: {
    SET_SELECTED_STAFF_ID: (state, value) => {
      state.selectedStaffId = value
    },
    SET_SELECTED_STAFF_NAME: (state, value) => {
      state.selectedStaffName = value
    },
    SET_REGIST_DIALOG: (state, value) => {
      state.registDialog = value
    },
    SET_DELETE_DIALOG: (state, value) => {
      state.deleteDialog = value
    },
  },
  actions: {
    setSelectedStaffId({ commit }, value) {
      commit("SET_SELECTED_STAFF_ID", value)
    },
    setSelectedStaffName({ commit }, value) {
      commit("SET_SELECTED_STAFF_NAME", value)
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