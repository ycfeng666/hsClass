import { createRouter, createWebHashHistory } from "vue-router";
import adminList from "./modules/admin";
import indexList from "./modules/main";

const constantRoutes = [
  {
    path: "/admin/login",
    name: "adminLogin",
    component: () => import("@/views/admin/login.vue"),
    meta: {
      title: "管理员登录",
    },
    needLogin: false,
  },
  {
    path: "/admin",
    name: "Admin",
    redirect: "/admin/home",
    children: adminList,
    component: () => import("@/views/admin/index.vue"),
    meta: {
      title: "管理员首页",
    },
    needLogin: true,
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/main/login.vue"),
    meta: {
      title: "用户登录",
    },
    needLogin: false,
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("@/views/main/register.vue"),
    meta: {
      title: "用户注册",
    },
    needLogin: false,
  },
  {
    path: "/",
    name: "Index",
    component: () => import("@/views/main/index.vue"),
    redirect: "/home",
    children: indexList,
    meta: {
      title: "用户首页",
    },
    needLogin: false,
  },
];
const router = createRouter({
  history: createWebHashHistory(),
  routes: constantRoutes,
});

// eslint-disable-next-line no-unused-vars
router.beforeEach((to, from, next) => {
  // if (to.matched.some((auto) => auto.needLogin)){
  // console.log(store);
  next();
  // }
});

export default router;
