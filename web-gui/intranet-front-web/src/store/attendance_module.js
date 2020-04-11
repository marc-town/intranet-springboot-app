const getDefaultState = () => {
  return {
    submitAttendance: false,
    staffId: 0,
    currentYear: '',
    currentMonth: '',
    totalWorkingTime: 0,
    totalNightTime: 0,
    totalOverTime: 0,
  }
}
const attendance = {
  namespaced: true,
  state: getDefaultState,
  mutations: {
    SET_ATTENDANCE: (state, summary) => {
      state.submitAttendance = true
      state.staffId = summary.staffId
      state.currentYear = summary.currentYear
      state.currentMonth = summary.currentMonth
      state.totalWorkingTime = summary.totalWorkingTime
      state.totalNightTime = summary.totalNightTime
      state.totalOverTime = summary.totalOverTime
    },
    RESET_ATTENDANCE: state => {
      Object.assign(state, getDefaultState());
    }
  },
  actions: {
    setAttendance: ({ commit }, summary) => {
      commit("SET_ATTENDANCE", summary)
    },
    resetAttendance: ({ commit }) => {
      commit('RESET_ATTENDANCE');
    }
  }
}

export default attendance