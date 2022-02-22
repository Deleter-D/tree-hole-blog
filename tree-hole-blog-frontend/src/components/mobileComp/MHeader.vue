<template>
  <div>
    <el-menu mode="horizontal"
             router
             :default-active="activeIndex"
             active-text-color="#f39c12"
             background-color="rgba(255,255,255,0.2)">
      <el-menu-item index="/mobile">首页</el-menu-item>
      <el-menu-item index="/mobile/list">文章</el-menu-item>
      <el-menu-item index="/mobile/comment">留言板</el-menu-item>
      <el-menu-item index="/mobile/about">关于我</el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: 'MHeader',
  data() {
    return {
      activeIndex: '',
    };
  },
  watch: {
    $route() {
      this.activeIndex = this.$route.path;
    },
  },
};
</script>

<style scoped>

</style>
