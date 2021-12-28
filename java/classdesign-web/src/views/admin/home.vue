<template>
  <el-descriptions title="信息" :column="2" border v-loading="data.tabLoading">
    <el-descriptions-item>
      <template #label> 前端系统版本 </template>
      {{ data.webVersion }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 前端系统依赖 </template>
      {{ data.webRelyOn }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 后端系统版本 </template>
      {{ data.systemVersion }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 后端系统依赖 </template>
      {{ data.systemRelyOn }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 用户数 </template>
      {{ data.userCount }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label> 发起投票数 </template>
      {{ data.voteCount }}
    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
import { defineComponent, reactive, onMounted, getCurrentInstance } from "vue";
export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;

    const data = reactive({
      webVersion: "1.0.0(beta1)-20211222",
      systemVersion: "",
      webRelyOn: "Vue3+Element-Plus",
      systemRelyOn: "",
      userCount: 0,
      voteCount: 0,
      tabLoading: false,
    });
    onMounted(() => {
      data.tabLoading = true;
      proxyFun.$api.get("/SystemInfoServlet", {}).then((res) => {
        data.tabLoading = false;
        if (res.data !== "NoPermission") {
          for (let key in res.data) {
            data[key] = res.data[key];
          }
        }
      });
    });
    return {
      data,
    };
  },
});
</script>

<style lang="sass" scoped></style>
