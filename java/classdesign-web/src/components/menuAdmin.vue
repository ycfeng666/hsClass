<template>
  <el-aside width="200px">
    <el-menu router="true" :default-active="data.default">
      <el-menu-item
        v-for="item in data.menuItem"
        :key="item.id"
        :index="item.id"
        :route="{ path: item.url }"
        >{{ item.text }}</el-menu-item
      >
    </el-menu>
  </el-aside>
</template>
<script>
import { defineComponent, reactive } from "vue";

export default defineComponent({
  setup() {
    const data = reactive({
      menuItem: [
        { id: 0, text: "控制台", name: "home", url: "/admin/home" },
        { id: 1, text: "用户管理", name: "user", url: "/admin/user" },
        { id: 2, text: "投票管理", name: "list", url: "/admin/vote" },
      ],
      default: 0,
    });
    return {
      data,
    };
  },
});
</script>
<style lang="scss" scoped></style>
