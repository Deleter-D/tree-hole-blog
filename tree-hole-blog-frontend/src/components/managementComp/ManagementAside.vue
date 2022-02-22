<template>
  <div class="aside">
    <br><br>
    <div style="text-align: center">
      <el-image style="height: 50%;width: 50%"
                fit="cover"
                :src="this.$store.state.imagesBaseUrl+'images/touxiang.jpg'"></el-image>
      <br><br>
      <el-row>
        <el-col :offset="1" :span="22">
          <el-menu background-color="#343751"
                   text-color="white"
                   router>
            <el-menu-item index="/management">首页</el-menu-item>
            <el-sub-menu index="1">
              <template #title>
                <span>文章管理</span>
              </template>
              <el-menu-item index="/management/list">文章列表</el-menu-item>
              <el-menu-item index="/management/add">添加文章</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="/management/taglist">标签管理</el-menu-item>
            <el-menu-item index="/management/commentlist">留言管理</el-menu-item>
            <el-sub-menu index="2">
              <template #title>
                <span>回收站</span>
              </template>
              <el-menu-item index="/management/deletedlist">已删除文章</el-menu-item>
              <el-menu-item index="/management/tagdeletedlist">已删除标签</el-menu-item>
              <el-menu-item index="/management/commentdeletedlist">已删除留言</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ManagementAside',
};
</script>

<style scoped>
.aside {
  background-color: #282c3c;
  min-height: 100vh;
  text-align: center;
}
</style>
