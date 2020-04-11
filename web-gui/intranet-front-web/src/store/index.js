import Vue from 'vue';
import Vuex from 'vuex';

import createPersistedState from 'vuex-persistedstate'

import attendance from './attendance_module';
import auth from './auth_module';
import common from './common_module';
import error from './error_module';
import snackbar from './snackbar_modules';
import staff from './staff_module';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    attendance,
    auth,
    common,
    error,
    snackbar,
    staff,
  },
  plugins: [createPersistedState({
    key: 'gsol-intranet-app',
    paths: ["auth"],
    storage: window.sessionStorage
  })]
});