<template>
  <img src="@/assets/main/img/bg3.jpg" class="background" />
  <div v-loading="data.tabLoading">
    <el-row class="row" align="middle">
      <el-col :span="4" />
      <el-col :span="16">
        <el-card class="tm">
          <el-scrollbar max-height="500px">
            <el-form :model="data.subjectData" label-position="top">
              <el-form-item
                v-for="(item, index) in data.subjectData"
                :key="item.sid"
                :label="index + 1 + '，' + item.subject"
              >
                <span
                  v-if="typeof item.remarks !== 'undefined'"
                  style="color: #909399"
                  >{{ item.remarks }}<br
                /></span>
                <el-radio-group v-model="data.optionList['s' + item.sid].val">
                  <el-radio
                    v-for="item2 in data.optionList['s' + item.sid]"
                    :key="item2.id"
                    :label="item2.text"
                  >
                  </el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item v-if="!data.isEmpty">
                <el-button
                  color="#626aef"
                  style="color: white"
                  @click="saveVote"
                  :disabled="data.user === ''"
                  >提交</el-button
                >
              </el-form-item>
            </el-form>
          </el-scrollbar>
          <el-empty v-if="data.isEmpty" description="暂无数据"></el-empty>
        </el-card>
      </el-col>
      <el-col :span="4" />
    </el-row>
  </div>
</template>

<script>
import { defineComponent, reactive, onMounted, getCurrentInstance } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;

    const router = useRouter();
    const store = useStore();
    const data = reactive({
      user: "",
      voteID: 0,
      subjectData: {},
      optionList: {},
      tabLoading: false,
      isEmpty: false,
    });
    const getData = () => {
      if (data.voteID !== 0) {
        data.tabLoading = true;
        proxyFun.$api
          .get("/SubjectListServlet", { vid: data.voteID })
          .then((res) => {
            data.tabLoading = false;
            if (res.data !== "NoPermission" && res.data !== "ParamIsEmpty") {
              if (Object.keys(res.data).length !== 0) {
                data.subjectData = res.data;
                data.subjectData.forEach((element) => {
                  data.optionList["s" + element.sid] = [];
                  for (let i1 = 1; i1 < 5; i1++) {
                    if (typeof element["obt" + i1] !== "undefined") {
                      data.optionList["s" + element.sid].push({
                        id: i1,
                        text: element["obt" + i1],
                      });
                    }
                  }
                });
                data.dvVoteShow = true;
              } else {
                data.isEmpty = true;
                proxyFun.$notify({
                  type: "warning",
                  title: "提示",
                  message: "管理员暂未创建题目",
                });
              }
            }
          });
      } else {
        data.isEmpty = true;
        proxyFun.$notify({
          type: "error",
          title: "提示",
          message: "请求失败",
        });
      }
    };
    const saveVote = () => {
      proxyFun.$message({
        message: "投票成功",
        type: "success",
      });
    };
    onMounted(() => {
      if (typeof router.currentRoute.value.query.id !== "undefined")
        data.voteID = router.currentRoute.value.query.id;
      getData();
      data.user = store.state.user;
    });
    return {
      data,
      saveVote,
    };
  },
});
</script>

<style lang="scss" scoped>
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
.tm {
  background: rgba(255, 255, 255, 0.8);
}
</style>
>
