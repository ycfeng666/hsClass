import axios from "axios";
const baseURL = "/Api";
const config = axios.create({
  baseURL: baseURL,
  timeout: 1000,
});
const api = axios.create(config);
api.post = function (url, param) {
  return new Promise((resolve, reject) => {
    axios({
      method: "post",
      url: baseURL + url,
      data: param,
      headers: { "content-type": "application/json;charset=utf-8" },
    })
      .then((response) => {
        if (response.status == 200) {
          resolve(response);
        } else {
          reject(response);
        }
      })
      .catch((err) => {
        reject(err);
      });
  });
};
api.get = function (url, param) {
  return new Promise((resolve, reject) => {
    axios({
      method: "get",
      url: baseURL + url,
      params: param,
    })
      .then((response) => {
        if (response.status == 200) {
          resolve(response);
        } else {
          reject(response);
        }
      })
      .catch((err) => {
        reject(err);
      });
  });
};
api.delete = function (url, param) {
  return new Promise((resolve, reject) => {
    axios({
      method: "delete",
      url: baseURL + url,
      params: param,
    })
      .then((response) => {
        if (response.status == 200) {
          resolve(response);
        } else {
          reject(response);
        }
      })
      .catch((err) => {
        reject(err);
      });
  });
};
// api.interceptors.response.use(
//   (response) => {
//     //拦截响应，做统一处理
//     // if (response.data.code) {
//     //   // console.log(response.status);
//     //   switch (response.status) {
//     //     case 301:
//     //       console.log("登录过期");
//     //   }
//     // }
//     return response;
//   },
//   //接口错误状态处理，也就是说无响应时的处理
//   (error) => {
//     return Promise.reject(error.response.status); // 返回接口返回的错误信息
//   }
// );
export default api;
