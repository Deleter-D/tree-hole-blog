<template>
  <div style="width: 81vw">
    <el-row>
      <el-col :span="24">
        <el-menu mode="horizontal"
                 router
                 :default-active="activeIndex"
                 active-text-color="#f39c12"
                 background-color="rgba(255,255,255,0.2)">
          <el-menu-item index="/"><span>首页</span></el-menu-item>
          <el-menu-item index="/list"><span>文章</span></el-menu-item>
          <el-menu-item index="/comment"><span>留言板</span></el-menu-item>
          <el-menu-item index="/about"><span>关于我</span></el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Header',
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
span {
  font-size: 16px;
}
</style>
