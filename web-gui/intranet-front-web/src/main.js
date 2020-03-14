import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as VeeValidate from 'vee-validate';

import vuetify from './plugins/vuetify';
import 'vuetify/dist/vuetify.min.css';
import '@mdi/font/css/materialdesignicons.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

// 多言語化
import VueI18n from 'vue-i18n'
import messages from './i18n/messages.json'
Vue.use(VueI18n);
const i18n = new VueI18n({
  locale: navigator.language.split('-')[0],
  fallbackLocale: 'ja',
  messages
});

Vue.use(VeeValidate)

Vue.config.productionTip = false

// Axiosのカスタマイズ
import http from './http';
Vue.prototype.$axios = http;

new Vue({
  router,
  store,
  vuetify,
  i18n,
  render: h => h(App)
}).$mount('#app')