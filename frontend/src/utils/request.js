import axios from 'axios';
import {Message} from 'element-ui';

const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url
  timeout: 5000 // request timeout
});

service.interceptors.response.use(
  response => {
    if (!response.data.success) {
      let error = response.data.error;
      console.log('err' + error);// for debug
      Message({
        message: error,
        type: 'error',
        duration: 2000
      });
      return Promise.reject(error);
    } else {
      return response
    }
  },
  error => {
    console.log('err' + error);// for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    });
    return Promise.reject(error);
  });
export default service;
