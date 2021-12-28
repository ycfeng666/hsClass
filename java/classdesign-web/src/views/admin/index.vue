<template>
  <el-container>
    <menu-Admin />
    <el-container>
      <el-header>
        <header-admin />
      </el-header>
      <el-main><router-view /></el-main>
    </el-container>
  </el-container>
</template>

<script>
import { defineComponent, reactive, onMounted, getCurrentInstance } from "vue";
import { useRouter } from "vue-router";
import headerAdmin from "@/components/headerAdmin.vue";
import menuAdmin from "@/components/menuAdmin.vue";
export default defineComponent({
  components: { headerAdmin, menuAdmin },
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;

    const router = useRouter();
    const data = reactive({});
    onMounted(() => {
      proxyFun.$api
        .get("/LoginServlet", {
          type: 0,
        })
        .then((res) => {
          if (res.data != null) {
            proxyFun.$eventBus.emit("user", res.data);
          } else {
            router.push("/admin/login");
          }
        });
    });
    return {
      data,
    };
  },
});
</script>

<style lang="scss" scoped>
a {
  text-decoration: none;
  color: #333333;
}
a:hover {
  color: #d60000;
}
.el-header {
  background: #3a6ea5;
  height: 65px;
  margin-bottom: 5px;
  padding: 0;
}
</style>
