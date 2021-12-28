export default [
  {
    path: "/admin/home",
    name: "adminHome",
    component: () => import("@/views/admin/home.vue"),
  },
  {
    path: "/admin/user",
    name: "adminUser",
    component: () => import("@/views/admin/user.vue"),
  },
  {
    path: "/admin/vote",
    name: "newsDetail",
    component: () => import("@/views/admin/vote.vue"),
  },
];
