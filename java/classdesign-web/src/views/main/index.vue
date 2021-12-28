<template>
  <header-main />
  <div><router-view /></div>
</template>

<script>
import { defineComponent, reactive, onMounted, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import headerMain from "../../components/headerMain.vue";
export default defineComponent({
  components: { headerMain },
  setup() {
    const proxy = getCurrentInstance();
    const proxyFun = proxy.appContext.config.globalProperties;
    const store = useStore();
    const data = reactive({});
    onMounted(() => {
      proxyFun.$api
        .get("/LoginServlet", {
          type: 1,
        })
        .then((res) => {
          if (res.data != null) store.commit("setUserData", res.data);
        });
    });
    return {
      data,
    };
  },
});
</script>
