export default [
  {
    path: "/home",
    name: "IndexHome",
    component: () => import("@/views/main/home.vue"),
  },
  {
    path: "/vote",
    name: "IndexVote",
    component: () => import("@/views/main/vote.vue"),
  },
];
