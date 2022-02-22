<template>
  <div>
    <el-card class="card">
      <span>{{ title }}</span>
      <hr>
      <slot></slot>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'ManagementHomeCard',
  props: ['title'],
};
</script>

<style scoped>
.card {
  width: 96%;
  padding-left: 2%;
  height: 300px;
}
</style>
