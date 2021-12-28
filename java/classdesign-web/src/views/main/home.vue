<template>
  <img src="@/assets/main/img/bg.jpg" class="background" />
  <el-row class="row">
    <el-col :span="2" />
    <el-col :span="16">
      <el-space wrap>
        <el-card
          v-for="item in data.tableData"
          :key="item.id"
          @click="goToVote(item)"
          class="card tm"
          shadow="always"
        >
          <template #header>
            <span>投票</span>
          </template>
          <b>投票名称：</b>{{ item.title }}
          <el-divider content-position="left"></el-divider>
          <b>创建人：</b>{{ item.createUserName }}
          <el-divider content-position="left"></el-divider>
          <b>开始时间：</b>{{ item.releaseTime }}
          <el-divider content-position="left"></el-divider>
          <b>结束时间：</b>{{ item.endTime }}
        </el-card>
      </el-space>
    </el-col>
    <el-col :span="2" />
  </el-row>
</template>

<script>
import { defineComponent, reactive, onMounted, getCurrentInstance } from "vue";
import { useRouter } from "vue-router";
export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;
    const router = useRouter();
    const data = reactive({
      tableData: [],
      tabLoading: false,
    });
    const goToVote = (res) => {
      router.push({ path: "/vote", query: { id: res.id } });
    };

    onMounted(() => {
      data.tabLoading = true;
      proxyFun.$api.get("/VoteListServlet", {}).then((res) => {
        data.tabLoading = false;
        data.tableData = res.data;
      });
    });
    return {
      data,
      goToVote,
    };
  },
});
</script>

<style lang="scss" scoped>
.card {
  width: 300px;
}
.tm {
  background: rgba(255, 255, 255, 0.8);
}
.row {
  margin-top: 20px;
}
.background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}
</style>
