<template>
  <el-scrollbar height="100vh" ref="m_scroll">
    <div>
      <MHeader></MHeader>
      <router-view/>
    </div>
  </el-scrollbar>
</template>

<script>
// eslint-disable-next-line import/extensions
import MHeader from '../../components/mobileComp/MHeader';

export default {
  name: 'MobileHome',
  components: { MHeader },
  watch: {
    $route() {
      this.$refs.m_scroll.setScrollTop(0);
    },
  },
  beforeRouteEnter(to, from, next) {
    document.querySelector('body')
      .setAttribute('style', 'margin:0;padding:0;');
    next();
  },
};
</script>

<style scoped>
</style>
