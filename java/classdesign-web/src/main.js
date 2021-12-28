import { createApp } from "vue";
import App from "./App.vue";
const app = createApp(App);

import router from "./router";
app.use(router);
import store from "./store";
app.use(store);

import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
app.use(ElementPlus);

import * as ElIconModules from "@element-plus/icons-vue";
import transElIconName from "./utils/ELIconModules";
for (let iconName in ElIconModules) {
  app.component(transElIconName(iconName), ElIconModules[iconName]);
}

import commonFun from "./utils/commonFun";
commonFun(app);

app.mount("#app");
