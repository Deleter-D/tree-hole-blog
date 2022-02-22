<template>
  <div style="width: 99%;">
    <el-card class="card">
      <el-skeleton :loading="loading" animated>
        <template #template>
          <el-skeleton-item variant="h2" style="width: 15vw;height: 23px"/>
          <br><br>
          <el-skeleton-item variant="text"/>
          <el-skeleton-item variant="text"/>
          <el-skeleton-item variant="text" style="width: 50vw;"/>
        </template>
      </el-skeleton>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'CommentPlateLoading',
  props: {
    loading: { type: Boolean },
  },
};
</script>

<style scoped>
.card {
  background-color: rgba(255, 255, 255, 0.2);
}
</style>
