<template>
  <div class="header">
    <div class="header__navbar">
      <div class="header_navitem">
        <a
          v-for="item in data.headerNavList"
          :key="item.id"
          @click="goTo(item.id)"
          >{{ item.text }}</a
        >
      </div>
    </div>
    <button class="header__login" v-if="data.user !== ''">
      <span @click="loginOut">{{ data.user.name || "注销" }}</span>
    </button>
    <button v-else class="header__login" @click="goToLogin">
      <span>登陆</span>
      <img src="../assets/main/img/login.png" />
    </button>
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
      headerNavList: [{ id: 0, text: "首页" }],
      user: "",
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
            .post("/LoginServlet", { type: 1, role: "user" })
            .then((res) => {
              proxyFun.$eventBus.off("user");
              if (res.data == "LoginOutSuccess") {
                router.go(0);
                store.commit("setUserData", "");
                sessionStorage.removeItem("user");
              }
            });
        })
        .catch(() => {});
    };
    onMounted(() => {
      data.user = store.state.user;
      console.log(typeof data.user);
    });
    const goTo = (id) => {
      switch (id) {
        case 0:
          router.push("/home");
          break;
      }
    };
    const goToLogin = () => {
      router.push("/login");
    };
    return {
      data,
      goTo,
      goToLogin,
      loginOut,
    };
  },
});
</script>

<style lang="scss" scoped>
$height: 65px;
.header {
  display: flex;
  width: 100%;
  height: $height;
  background-color: rgba(17, 17, 17, 0.6);
  box-shadow: 0px 3px 7px 0px rgb(0 0 0 / 35%);
  transition: height 0.8s ease-out;
  .header__navbar {
    display: flex;
    width: 80%;
    height: $height;
    display: inline-block;
    list-style-type: none;
    padding-right: 10px;
    margin-left: 40px;
    .header_navitem {
      a {
        position: relative;
        left: 240px;
        width: 40px;
        height: $height;
        margin: 0 30px;
        line-height: $height;
        font-size: 17px;
        color: #ccc;
        text-align: center;
        cursor: pointer;
      }
      a:hover {
        text-shadow: 0 0 10px #69e0ff, 0 0 40px #69e0ff, 0 0 40px #69e0ff;
        color: #fff;
      }
    }
  }
  .header__login {
    position: relative;
    width: 100px;
    height: 65px;
    display: flex;
    align-items: center;
    padding: 0 10px;
    border: 0;
    background: none;
    font-size: 17px;
    color: #ccc;
    cursor: pointer;
    img {
      margin-left: 18px;
      width: 27px;
      height: 27px;
      opacity: 0.6;
    }
  }
  .header__login :hover {
    color: white;
  }
}
</style>
>
