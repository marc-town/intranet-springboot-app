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
    // クライアントエラー時の処理
    if (err.response.status === 400) {
      alert(`output by interceptors: ${JSON.stringify(err)}`);
      store.dispatch('error/setErrorDialog', true);
    // 認証エラー時の処理
    } else if (err.response.status === 401) {
      alert(`output by interceptors: ${JSON.stringify(err)}`);
      router.push('/login')
    // 認可エラー時の処理
    } else if (err.response.status === 403) {
      alert(`output by interceptors: ${JSON.stringify(err)}`);
      router.push('/error')  
    // NotFound時の処理
    } else if (err.response.status === 404) {
      alert(`output by interceptors: ${JSON.stringify(err)}`);
      router.push('/error')
    // システムエラー時の処理
    } else if (err.response.status === 500) {
      alert(`output by interceptors: ${JSON.stringify(err)}`);
      const error = {
        'errorDialog': true,
        'status': err.response.status,
        'code': 'Internal Server Error',
        'message': err.message
      };
      store.dispatch('error/setErrorDialog', error);
    }
    return Promise.reject(err)
  });

export default axios;