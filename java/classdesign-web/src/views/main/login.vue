<template>
  <div class="background" />
  <div class="box">
    <div class="left bg_Login"></div>
    <div class="right">
      <h4>用户登陆</h4>
      <el-form
        ref="loginFormRef"
        :model="data.LoginForm"
        :rules="data.loginRules"
      >
        <el-form-item prop="name" class="acc">
          <el-input
            type="text"
            v-model="data.LoginForm.name"
            placeholder="请输入用户名"
          />
        </el-form-item>
        <el-form-item prop="password" class="acc">
          <el-input
            v-model="data.LoginForm.password"
            show-password
            placeholder="请输入密码"
          />
        </el-form-item>

        <el-button
          class="submit"
          @click="Login"
          @keyup.enter="Login"
          :loading="data.LoginOrder.btnLoading"
          >登陆</el-button
        >
      </el-form>
      <div class="fn">
        <el-space wrap>
          <el-link type="primary" @click="goTo(0)">用户注册</el-link>
          <el-link type="success" @click="goTo(1)">返回首页</el-link>
        </el-space>
      </div>
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
        name: "test1",
        password: "",
        type: 0,
        role: "user",
      },
      LoginOrder: {
        btnLoading: false,
      },
      loginRules: {
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    });
    const Login = () => {
      proxy.refs.loginFormRef.validate((v) => {
        if (v) {
          data.LoginOrder.btnLoading = true;
          proxyFun.$api.post("/LoginServlet", data.LoginForm).then((res) => {
            data.LoginOrder.btnLoading = false;
            if (res.data == "Success") router.push("/home");
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
    const goTo = (id) => {
      switch (id) {
        case 0:
          router.push("/register");
          break;
        case 1:
          router.push("/home");
          break;
      }
    };
    onMounted(() => {
      proxyFun.$api
        .get("/LoginServlet", {
          type: 1,
        })
        .then((res) => {
          if (res.data != null) {
            router.push("/home");
          }
        })
        .catch(() => {
          proxyFun.$message({
            message: "请求错误",
            type: "error",
          });
        });
    });
    return {
      data,
      Login,
      goTo,
    };
  },
});
</script>

<style lang="scss" scoped>
.background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%) no-repeat;
}
.box {
  display: flex;
  overflow: hidden;
  width: 600px;
  height: 400px;
  background-color: rgba(255, 255, 255, 60%);
  border-radius: 15px;
  margin: 10% auto;
  box-shadow: 0 0 10px 2px rgb(0 0 0 / 10%);
  .left {
    position: relative;
    width: 35%;
    height: 100%;
    background-color: skyblue;
  }
  .left::before {
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
    opacity: 80%;
  }
  .right {
    display: flex;
    width: 65%;
    flex-direction: column;
    align-items: center;
    h4 {
      color: rgb(144, 129, 241);
      font-size: 30px;
      margin-top: 20px;
    }
    form {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      .acc {
        outline: none;
        width: 80%;
        height: 50px;
        font-size: 16px;
        padding: 5px 0 0 10px;
        border: none;
        // border-bottom: 1px solid rgb(144, 129, 241);
        color: rgb(144, 129, 241);
      }
      .acc:focus {
        outline: none;
        color: rgb(144, 129, 241);
        padding: 10px 0 0 16px;
      }
      .submit {
        width: 60%;
        height: 50px;
        color: #f6f6f6;
        background-image: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
        font-size: 14px;
        border: none;
        border-radius: 5px;
        margin: 20px 0 0 50%;
        transform: translateX(-50%);
      }
      .submit:hover {
        box-shadow: 0 0 20px -5px rgb(0 0 0 / 15%);
      }
    }
    .fn {
      display: flex;
      justify-content: space-between;
      margin-top: 10px;
      width: 70%;
      a {
        font-size: 13px;
        padding: 10px 20px;
        color: #666;
      }
      a:hover {
        color: rgb(144, 129, 241);
      }
    }
  }
}
</style>
