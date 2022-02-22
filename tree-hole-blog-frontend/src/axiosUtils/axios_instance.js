import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8081',
});

export function get(url, params) {
  return instance.get(url, params);
}

export function post(url, params) {
  return instance.post(url, params, {
    transformRequest: [
      function (data) {
        let str = '';
        // eslint-disable-next-line guard-for-in,no-restricted-syntax
        for (const key in data) {
          str += `${encodeURIComponent(key)}=${encodeURIComponent(data[key])}&`;
        }
        return str;
      },
    ],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
  });
}

export function put(url, params) {
  return instance.put(url, params, {
    transformRequest: [
      function (data) {
        let str = '';
        // eslint-disable-next-line guard-for-in,no-restricted-syntax
        for (const key in data) {
          str += `${encodeURIComponent(key)}=${encodeURIComponent(data[key])}&`;
        }
        return str;
      },
    ],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
  });
}

export function patch(url, params) {
  return instance.patch(url, params, {
    transformRequest: [
      function (data) {
        let str = '';
        // eslint-disable-next-line guard-for-in,no-restricted-syntax
        for (const key in data) {
          str += `${encodeURIComponent(key)}=${encodeURIComponent(data[key])}&`;
        }
        return str;
      },
    ],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
  });
}

export function del(url, params) {
  return instance.delete(url, params);
}

export function upload(url, params) {
  return instance.post(url, params, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
}

export default instance;
