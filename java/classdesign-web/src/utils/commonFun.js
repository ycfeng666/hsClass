import api from "@/api";
import mitt from "mitt";
import dayjs from "dayjs";
import "dayjs/locale/zh-cn";

export default function commonFun(app) {
  // 请求
  app.config.globalProperties.$api = api;
  // 事务总线
  app.config.globalProperties.$eventBus = mitt();
  // 时间
  app.config.globalProperties.$dayjs = dayjs;
}
