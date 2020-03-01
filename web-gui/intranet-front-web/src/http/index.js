// axios setting
import Axios from 'axios';

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
  error => {
    Promise.reject(error)
  });

/*
 * The interceptor here check the http response errors
 */
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    // 認証エラー時の処理
    if (error.response.status === 401) {
      alert(`output by interceptors: ${JSON.stringify(error)}`)
    // システムエラー時の処理
    } else if (error.response.status === 500) {
      alert(`output by interceptors: ${JSON.stringify(error)}`)
    }
    return Promise.reject(error)
  });

export default axios;