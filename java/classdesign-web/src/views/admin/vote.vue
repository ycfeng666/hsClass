<template>
  <el-button type="primary" @click="openVoteAdd(0)">创建新投票</el-button>
  <el-table
    :data="data.tableData"
    style="width: 100%"
    v-loading="data.tabLoading"
  >
    <el-table-column
      label="主标题"
      prop="title"
      show-overflow-tooltip="true"
      align="center"
    />
    <el-table-column label="发布时间" prop="releaseTime" align="center" />
    <el-table-column label="结束时间" prop="endTime" align="center" />
    <el-table-column label="发布用户" prop="createUserName" align="center" />
    <el-table-column label="操作" align="center">
      <template v-slot="scope">
        <el-space wrap>
          <el-link type="primary" @click="openVoteAdd(1, scope.row)"
            >修改投票</el-link
          >
          <el-link type="warning" @click="openSubjectAddEdit(scope.row)"
            >操作投票</el-link
          >
          <el-link type="success" @click="openVoteDv(scope.row)"
            >预览投票</el-link
          >
          <el-link type="danger" @click="delVote(scope.row)">删除投票</el-link>
        </el-space>
      </template>
    </el-table-column>
    <template #empty><el-empty description="暂无数据"></el-empty></template>
  </el-table>
  <!-- 查看投票 -->
  <el-dialog :title="data.dvVoteShowSpan" v-model="data.dvVoteShow" width="30%">
    <el-scrollbar max-height="300px">
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
      </el-form>
    </el-scrollbar>
    <template #footer class="dialog-footer">
      <el-button @click="data.dvVoteShow = false">关闭</el-button>
    </template>
  </el-dialog>
  <!-- 修改题目 -->
  <el-dialog
    :title="data.dvVoteShowSpan"
    v-model="data.addEditSubjectShow"
    width="45%"
  >
    <el-button type="primary" @click="addRow(1)">新增题目</el-button>
    <el-table
      :data="data.addSubjectListData"
      max-height="250"
      v-loading="data.tabLoading2"
    >
      <el-table-column
        label="题目"
        width="200"
        align="center"
        show-overflow-tooltip="true"
        fixed
      >
        <template v-slot="scope">
          {{ data.addSubjectListData[scope.$index].subject }}
        </template>
      </el-table-column>
      <el-table-column
        label="备注"
        width="200"
        show-overflow-tooltip="true"
        align="center"
      >
        <template v-slot="scope">
          {{ data.addSubjectListData[scope.$index].remarks }}
        </template>
      </el-table-column>
      <el-table-column label="选项1" width="150" align="center">
        <template v-slot="scope">
          {{ data.addSubjectListData[scope.$index].obt1 }}
        </template>
      </el-table-column>
      <el-table-column label="选项2" width="150" align="center">
        <template v-slot="scope">
          {{ data.addSubjectListData[scope.$index].obt2 }}
        </template>
      </el-table-column>
      <el-table-column label="选项3" width="150" align="center">
        <template v-slot="scope">
          {{ data.addSubjectListData[scope.$index].obt3 }}
        </template>
      </el-table-column>
      <el-table-column label="选项4" width="150" align="center">
        <template v-slot="scope">
          {{ data.addSubjectListData[scope.$index].obt4 }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center" fixed="right">
        <template v-slot="scope">
          <el-space wrap>
            <el-link type="primary" @click="addRow(2, scope.row)"
              >编辑题目</el-link
            >
            <el-link type="danger" @click="delRow(scope.row)">删除题目</el-link>
          </el-space>
        </template>
      </el-table-column>
    </el-table>
    <template #footer class="dialog-footer">
      <el-button @click="data.addEditSubjectShow = false">关闭</el-button>
    </template>
  </el-dialog>
  <!-- 新增与编辑选项 -->
  <el-dialog
    :title="data.dvSubjectSpan"
    v-model="data.addEditSubjectShowAgain"
    width="25%"
  >
    <el-scrollbar max-height="300px">
      <el-form
        :model="data.addSubjectData"
        :rules="data.addSubjectRules"
        ref="subjectDataRef"
      >
        <el-form-item label="题目" prop="subject">
          <el-input v-model="data.addSubjectData.subject" />
        </el-form-item>
        <el-form-item label="备注"
          ><el-input v-model="data.addSubjectData.remarks" />
        </el-form-item>
        <el-form-item label="选项1">
          <el-input v-model="data.addSubjectData.obt1"
        /></el-form-item>
        <el-form-item label="选项2">
          <el-input v-model="data.addSubjectData.obt2"
        /></el-form-item>
        <el-form-item label="选项3">
          <el-input v-model="data.addSubjectData.obt3"
        /></el-form-item>
        <el-form-item label="选项4"
          ><el-input v-model="data.addSubjectData.obt4"
        /></el-form-item>
      </el-form>
    </el-scrollbar>

    <template #footer class="dialog-footer">
      <el-button type="primary" @click="addEditSubject">提交</el-button>
      <el-button @click="data.addEditSubjectShowAgain = false">关闭</el-button>
    </template>
  </el-dialog>
  <!-- 创建新投票 -->
  <el-dialog title="创建新投票" v-model="data.addVoteShow" width="30%">
    <el-form
      :model="data.addVoteData"
      :rules="data.voteRules"
      ref="voteDataRef"
    >
      <el-form-item label="主标题" prop="title">
        <el-input v-model="data.addVoteData.title"></el-input>
      </el-form-item>
      <el-form-item label="时间段" prop="time">
        <el-date-picker
          v-model="data.addVoteData.time"
          range-separator="To"
          start-placeholder="发布时间"
          end-placeholder="结束时间"
          type="daterange"
          value-format="YYYY-MM-DD"
        ></el-date-picker>
      </el-form-item>
      <el-form-item> </el-form-item>
    </el-form>
    <template #footer class="dialog-footer">
      <el-button type="primary" @click="addVote" :loading="data.btnLoading"
        >提交</el-button
      >
      <el-button @click="data.addVoteShow = false">关闭</el-button>
    </template>
  </el-dialog>
</template>
<script>
import { defineComponent, reactive, getCurrentInstance, onMounted } from "vue";

export default defineComponent({
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;
    const data = reactive({
      user: {},
      tableData: [],
      dvVoteShow: false,
      addVoteShow: false,
      addEditSubjectShow: false,
      addEditSubjectShowAgain: false,
      tabLoading: false,
      tabLoading2: false,
      btnLoading: false,
      dvVoteShowSpan: "",
      dvSubjectSpan: "",
      clickVid: 0,
      subjectData: {},
      addVoteData: {},
      addSubjectListData: [{}],
      addSubjectData: {},
      optionList: {},
      voteRules: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        time: [
          {
            type: "array",
            required: true,
            message: "请输入时间段",
            trigger: "change",
          },
        ],
      },
      addSubjectRules: {
        subject: [{ required: true, message: "请输入标题", trigger: "blur" }],
      },
    });
    const getData = () => {
      data.tabLoading = true;
      proxyFun.$api.get("/VoteListServlet", {}).then((res) => {
        data.tabLoading = false;
        data.tableData = res.data;
      });
    };
    const getVoteData = (vid, type) => {
      proxyFun.$api.get("/SubjectListServlet", { vid: vid }).then((res) => {
        if (type === 0) data.tabLoading = false;
        else if (type === 1) data.tabLoading2 = false;
        if (res.data !== "NoPermission" && res.data !== "ParamIsEmpty") {
          if (Object.keys(res.data).length !== 0) {
            data.addSubjectListData = res.data;
          } else {
            data.addSubjectListData.push({});
          }
          if (type === 0) data.addEditSubjectShow = true;
        }
      });
    };
    const openVoteDv = (res) => {
      data.tabLoading = true;
      data.dvVoteShowSpan = res.title;
      data.subjectData = {};
      data.optionList = {};
      proxyFun.$api.get("/SubjectListServlet", { vid: res.id }).then((res) => {
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
            proxyFun.$notify({
              type: "warning",
              title: "提示",
              message: "请先创建题目",
            });
          }
        }
      });
    };
    const openVoteAdd = (type, row) => {
      data.addVoteShow = true;
      data.addVoteData = {};
      if (type === 0) data.addVoteData.createUser = data.user.userid;
      else {
        data.addVoteData = JSON.parse(JSON.stringify(row));
        data.addVoteData.time = [
          data.addVoteData.releaseTime,
          data.addVoteData.endTime,
        ];
      }
    };
    const addVote = () => {
      proxy.refs.voteDataRef.validate((v) => {
        if (v) {
          data.btnLoading = true;
          let tempData = {};
          for (const key in data.addVoteData) {
            tempData[key] = data.addVoteData[key];
            if (key === "time") {
              tempData.releaseTime = data.addVoteData[key][0];
              tempData.endTime = data.addVoteData[key][1];
            }
          }
          proxyFun.$api.post("/VoteListServlet", tempData).then((res) => {
            data.addVoteShow = false;
            data.btnLoading = false;
            getData();
            if (res.data == "Success") {
              proxyFun.$message({
                message: "保存成功",
                type: "success",
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
    const delVote = (row) => {
      proxyFun.$messageBox
        .confirm("确定删除该用户?操作将不可撤回", "删除用户", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          proxyFun.$api
            .delete("/VoteListServlet", { id: row.id })
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
    const openSubjectAddEdit = (res) => {
      data.tabLoading = true;
      data.dvVoteShowSpan = res.title;
      data.clickVid = res.id;
      data.addSubjectListData = [];
      getVoteData(data.clickVid, 0);
    };
    const addRow = (type, row) => {
      data.addSubjectData = {};
      if (type === 1) {
        data.dvSubjectSpan = "新增题目";
        data.addSubjectData.vid = data.clickVid;
      } else if (type === 2) {
        data.dvSubjectSpan = "编辑题目-" + row.subject;
        data.addSubjectData = row;
      } else {
        return false;
      }
      data.addEditSubjectShowAgain = true;
    };
    const delRow = (row) => {
      proxyFun.$messageBox
        .confirm("确定删除该用户?操作将不可撤回", "删除用户", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(() => {
          proxyFun.$api
            .delete("/SubjectListServlet", { id: row.sid })
            .then((res) => {
              data.tabLoading2 = true;
              getVoteData(row.vid, 1);
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
    const addEditSubject = () => {
      proxy.refs.subjectDataRef.validate((v) => {
        if (v) {
          proxyFun.$api
            .post("/SubjectListServlet", data.addSubjectData)
            .then((res) => {
              if (res.data == "Success") {
                data.addEditSubjectShowAgain = false;
                data.tabLoading2 = true;
                getVoteData(data.addSubjectData.vid, 1);
                proxyFun.$message({
                  message: "保存成功",
                  type: "success",
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
    onMounted(() => {
      getData();
      proxyFun.$eventBus.on("user", (item) => {
        data.user = item;
      });
    });
    return {
      data,
      openVoteDv,
      openVoteAdd,
      addVote,
      delVote,
      openSubjectAddEdit,
      addRow,
      delRow,
      addEditSubject,
    };
  },
});
</script>

<style lang="scss" scoped>
.roll-dialog {
  height: 400px;
  overflow: auto;
}
</style>
