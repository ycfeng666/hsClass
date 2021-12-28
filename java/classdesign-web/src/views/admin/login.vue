<template>
  <div class="loginViews">
    <img src="@/assets/admin/img/bg.jpg" class="background" />
    <div class="bigBox">
      <h1>管理员登陆</h1>
      <el-form
        ref="loginFormRef"
        :model="data.LoginForm"
        :rules="data.loginRules"
        label-width="80px"
      >
        <el-form-item label="用户名" prop="name">
          <el-input v-model="data.LoginForm.name" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="data.LoginForm.password" show-password />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="Login"
            @keyup.enter="Login"
            :loading="data.LoginOrder.btnLoading"
            >登陆</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { defineComponent, reactive, getCurrentInstance, onMounted } from "vue";
import { useRouter } from "vue-router";
export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;
    const router = useRouter();
    const data = reactive({
      LoginForm: {
        name: "admin",
        password: "123456",
        type: 0,
        role: "admin",
      },
      LoginOrder: {
        btnLoading: false,
      },
      loginRules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    });
    onMounted(() => {
      proxyFun.$api
        .get("/LoginServlet", {
          type: 0,
        })
        .then((res) => {
          if (res.data != null) {
            router.push("/admin/home");
          }
        })
        .catch(() => {
          proxyFun.$message({
            message: "请求错误",
            type: "error",
          });
        });
    });
    const Login = () => {
      proxy.refs.loginFormRef.validate((v) => {
        if (v) {
          data.LoginOrder.btnLoading = true;
          proxyFun.$api.post("/LoginServlet", data.LoginForm).then((res) => {
            data.LoginOrder.btnLoading = false;
            if (res.data == "Success") router.push("/admin/home");
            else if (res.data == "UserIsEmpty")
              proxyFun.$message({
                message: "用户不存在",
                type: "error",
              });
            else {
              proxyFun.$message({
                message: "请求错误",
                type: "error",
              });
            }
          });
        } else return false;
      });
    };
    return {
      data,
      Login,
    };
  },
});
</script>
<style lang="scss" scoped>
.loginViews {
  height: 100%;
  margin: 0;
  padding: 0;
  .background {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
  }
  .bigBox {
    margin: 0 auto;
    margin-top: 100px;
    padding: 20px 20px;
    background: rgba(255, 255, 255, 0.8);
    width: 25%;
    height: 250px;
    border-radius: 20px;
    text-align: center;
    h1 {
      background-image: linear-gradient(to right, orange, purple);
      background-clip: text;
      -webkit-text-fill-color: transparent;
      color: transparent;
      font-size: 30px;
    }
  }
}
</style>
