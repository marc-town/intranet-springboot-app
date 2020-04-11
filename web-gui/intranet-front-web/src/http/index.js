// axios setting
import Axios from 'axios';
import router from '../router'
import store from '../store'

const axios = Axios.create({
  baseURL: process.env.VUE_APP_API_URL,
  headers: {
    'Content-Type': 'application/json'
  },
  responseType: 'json',
  responseEncoding: 'utf8',
  // withCredentials: true
})

/*
 * The interceptor here ensures that we check for the token in local storage every time an ajax request is made
 */
axios.interceptors.request.use(
  config => {
    const sessionData = JSON.parse(sessionStorage.getItem('gsol-intranet-app'));
    const token = sessionStorage.length == 0 ? false : sessionData.auth.token;
    if (token) {
      config.headers['Authorization'] = `Bearer ${ token }`
    }
    return config;
  },
  err => {
    Promise.reject(err)
  });

/*
 * The interceptor here check the http response errors
 */
axios.interceptors.response.use(
  response => {
    return response;
  },
  err => {
    let path = '/error';
    let error = {};
    switch (err.response.status) {
      case 400:
        break;
      case 401:
        path = '/login';
        router.push(path);
        break;
      // 認可エラー時の処理
      case 403:
        router.push(path);
        break;
      // NotFound時の処理
      case 404:
        router.push(path);
        break;
      // MethodNotAllow時の処理
      case 405:
        router.push(path);
        break;
      // システムエラー時の処理
      case 500:
        error = {
          'errorDialog': true,
          'status': err.response.status,
          'code': 'Internal Server Error',
          'message': err.message
        };
        store.dispatch('error/setErrorDialog', error);
        break;
    }
    
    return Promise.reject(err)
  });

export default axios;