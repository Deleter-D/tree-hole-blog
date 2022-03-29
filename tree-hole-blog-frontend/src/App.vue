<template>
  <div class="app">
    <el-scrollbar ref="scroll" height="100vh">
      <div>
        <el-row>
          <el-col :span="4">
            <Aside></Aside>
          </el-col>
          <el-col :span="20">
            <Header></Header>
            <router-view></router-view>
          </el-col>
        </el-row>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
// eslint-disable-next-line import/extensions
import Header from './components/Header';
// eslint-disable-next-line import/extensions
import Aside from './components/Aside';

export default {
  components: {
    Aside,
    Header,
  },
  watch: {
    $route() {
      this.$refs.scroll.setScrollTop(0);
    },
  },
  methods: {
    isMobile() {
      const flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i);
      return flag;
    },
  },
  mounted() {
    document.querySelector('body')
      .setAttribute('style', 'margin:0;padding:0;');
  },
  created() {
    if (this.isMobile()) {
      window.location.href = '/mobile';
    }
  },
};
</script>

<style scoped>
.app {
  min-height: 100vh;
  /*background-image: url("assets/background.jpg");*/
  /*background-attachment: fixed;*/
  background: url("assets/background.jpg") no-repeat center center;
  background-size: 100% 100%;
}
</style>
