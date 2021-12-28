import { createStore } from "vuex";

const storeMaker = (state) => {
  // 初始化
  Object.keys(state).map((key) => {
    // 判断类型获取本地存储数据
    if (typeof state[key] === "object") {
      if (
        sessionStorage.getItem(key) &&
        JSON.parse(sessionStorage.getItem(key))
      ) {
        state[key] = JSON.parse(sessionStorage.getItem(key));
      }
    } else if (typeof state[key] === "number") {
      if (
        sessionStorage.getItem(key) &&
        parseInt(sessionStorage.getItem(key))
      ) {
        state[key] = parseInt(sessionStorage.getItem(key));
      }
    } else {
      if (sessionStorage.getItem(key)) {
        state[key] = sessionStorage.getItem(key);
      }
    }
  });
  // 重写set处理
  return new Proxy(state, {
    set: function (target, key, value) {
      let temp = value;
      if (typeof temp === "object") {
        temp = JSON.stringify(temp);
      }

      sessionStorage.setItem(key, temp);
      return Reflect.set(target, key, value);
    },
  });
};

export default createStore({
  state: storeMaker({
    adminUser: "", // 管理员用户信息
    user: "", // 用户信息
  }),
  mutations: {
    // 管理员用户信息
    setAdminUserData(state, data) {
      sessionStorage.setItem("adminUser", data);
      state.adminUser = data;
    },
    // 用户信息
    setUserData(state, data) {
      sessionStorage.setItem("user", data);
      state.user = data;
    },
    // 修改消息导航栏
    // setNewsNav(state, data) {
    //   state.newsNav = data;
    // },
  },
  actions: {
    getUser(state) {
      return state.user;
    },
  },
  modules: {},
});
