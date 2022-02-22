<template>
  <div style="width: 99%;">
    <el-card class="card">
      <h2>{{ comment.commentName }}&emsp;说：</h2>
      <div class="content">
        {{ comment.commentContent }}
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'CommentPlate',
  props: {
    comment: {},
  },
};
</script>

<style scoped>

.card {
  background-color: rgba(255, 255, 255, 0.2);
}

.content {
  text-indent: 2em;
}
</style>
