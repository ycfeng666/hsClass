<template>
  <el-row align="middle">
    <el-col :span="12">
      <a>
        <img src="@/assets/admin/img/logo.png" />
      </a>
    </el-col>
    <el-col :span="12">
      <div class="user">
        <el-dropdown>
          <el-icon color="white"><i-setting /></el-icon>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                v-for="item in data.dropdownList"
                :key="item.id"
                @click="clickFun(item.id)"
              >
                {{ item.name }}
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span>{{ data.user.name }}</span>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import { defineComponent, getCurrentInstance, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();

    const proxyFun = proxy.appContext.config.globalProperties;
    const router = useRouter();

    const data = reactive({
      user: "",
      dropdownList: [
        { id: 0, name: "返回首页" },
        { id: 1, name: "个人主页" },
        { id: 2, name: "注销登陆" },
      ],
    });

    onMounted(() => {
      proxyFun.$eventBus.on("user", (item) => {
        data.user = item;
      });
    });
    const loginOut = () => {
      proxyFun.$messageBox
        .confirm("确定要注销登陆吗?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          proxyFun.$api
            .post("/LoginServlet", { type: 1, role: "admin" })
            .then((res) => {
              proxyFun.$eventBus.off("user");
              if (res.data == "LoginOutSuccess") router.push("/admin/login");
            });
        });
    };
    const clickFun = (id) => {
      switch (id) {
        case 0:
          router.push("/");
          break;
        case 1:
          break;
        case 2:
          loginOut();
          break;
      }
    };

    return {
      data,
      clickFun,
    };
  },
});
</script>
<style lang="scss" scoped>
.user {
  float: right;
  margin: 0 10px 0 0;
  font-size: 24px;
  color: white;
  span {
    margin-left: 15px;
  }
}
</style>
