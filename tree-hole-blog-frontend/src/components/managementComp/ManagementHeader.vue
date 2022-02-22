<template>
  <div class="header">
    <div class="content">
      树洞后台管理系统
    </div>
  </div>
</template>

<script>
export default {
  name: 'ManagementHeader',
};
</script>

<style scoped>
.header {
  height: 55px;
  background-color: #3c74e4;
  background-image: linear-gradient(90deg, #3c74e4 0%, #549cf4 46%, #3c74e4 100%);
}

.content {
  text-align: center;
  padding-top: 15px;
  font-size: 25px;
  font-family: 黑体;
  color: white;
}
</style>
