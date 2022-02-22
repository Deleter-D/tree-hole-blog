<template>
  <el-row>
    <el-col :span="4">
      <ManagementAside></ManagementAside>
    </el-col>
    <el-col :span="20">
      <ManagementHeader></ManagementHeader>
      <br>
      <router-view></router-view>
    </el-col>
  </el-row>
</template>

<script>
// eslint-disable-next-line import/extensions
import ManagementHeader from '../../components/managementComp/ManagementHeader';
// eslint-disable-next-line import/extensions
import ManagementAside from '../../components/managementComp/ManagementAside';

export default {
  name: 'ManagementHome',
  components: {
    ManagementAside,
    ManagementHeader,
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
