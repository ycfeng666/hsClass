<template>
  <el-button type="primary" @click="openUserDv(1)">新增</el-button>
  <el-table
    :data="data.tableData"
    style="width: 100%"
    v-loading="data.tabLoading"
  >
    <el-table-column prop="account" label="账号" align="center" />
    <el-table-column prop="name" label="名字" align="center" />
    <el-table-column label="是否为管理员" align="center">
      <template v-slot="scope">
        <span>{{ scope.row.isAdmin == 1 ? "是" : "否" }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center">
      <template v-slot="scope">
        <el-button type="primary" @click="openUserDv(2, scope.row)"
          >修改</el-button
        >
        <el-button
          v-if="scope.row.isAdmin != 1"
          type="danger"
          @click="delUser(scope.row)"
          >删除</el-button
        >
      </template>
    </el-table-column>
    <template #empty><el-empty description="暂无数据"></el-empty></template>
  </el-table>
  <el-dialog
    :title="data.dvUserAddEditSpan"
    v-model="data.dvUserAddEdit"
    :append-to-body="true"
    width="30%"
  >
    <el-scrollbar max-height="300px">
      <el-form
        :model="data.addEditData"
        ref="userDataRef"
        :rules="data.userRules"
      >
        <el-form-item label="账户" prop="account">
          <el-input v-model="data.addEditData.account" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="data.addEditData.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="data.addEditData.password"
            clearable
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
    </el-scrollbar>

    <template #footer class="dialog-footer">
      <el-button @click="data.dvUserAddEdit = false">取 消</el-button>
      <el-button type="primary" @click="saveUser" :loading="data.btnLoading"
        >确 定</el-button
      >
    </template>
  </el-dialog>
</template>

<script>
import { defineComponent, onMounted, reactive, getCurrentInstance } from "vue";
export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;

    const data = reactive({
      tableData: [],
      dvUserAddEdit: false,
      dvUserAddEditSpan: "",
      addEditData: {},
      tabLoading: false,
      btnLoading: false,
      userRules: {
        account: [{ required: true, message: "请输入账户", trigger: "blur" }],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    });
    const getData = () => {
      data.tabLoading = true;
      proxyFun.$api.get("/UserListServlet", {}).then((res) => {
        data.tabLoading = false;
        if (res.data !== "NoPermission") {
          data.tableData = res.data;
        }
      });
    };
    const openUserDv = (type, row) => {
      data.addEditData = {};
      if (type == 1) {
        data.dvUserAddEditSpan = "新增用户";
        data.addEditData.userid = 0;
      } else if (type == 2) {
        data.addEditData = JSON.parse(JSON.stringify(row));
        data.dvUserAddEditSpan = "修改用户";
      } else return;
      data.dvUserAddEdit = true;
    };
    const saveUser = () => {
      proxy.refs.userDataRef.validate((v) => {
        if (v) {
          data.btnLoading = true;
          proxyFun.$api
            .post("/UserListServlet", data.addEditData)
            .then((res) => {
              data.dvUserAddEdit = false;
              data.btnLoading = false;
              getData();
              if (res.data == "Success") {
                proxyFun.$message({
                  message: "保存成功",
                  type: "success",
                });
              } else if (res.data == "UserIsHave") {
                proxyFun.$message({
                  message: "用户已存在请重新添加",
                  type: "warning",
                });
              } else {
                proxyFun.$message({
                  message: "请求错误",
                  type: "error",
                });
              }
            });
        } else return false;
      });
    };
    const delUser = (row) => {
      proxyFun.$messageBox
        .confirm("确定删除该用户?操作将不可撤回", "删除用户", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          proxyFun.$api
            .delete("/UserListServlet", { id: row.userid })
            .then((res) => {
              getData();
              if (res.data == "Success") {
                proxyFun.$message({
                  message: "删除成功",
                  type: "success",
                });
              } else {
                proxyFun.$message({
                  message: "请求错误",
                  type: "error",
                });
              }
            });
        });
    };
    onMounted(() => {
      getData();
    });
    return {
      data,
      openUserDv,
      saveUser,
      delUser,
    };
  },
});
</script>

<style lang="sass" scoped></style>
