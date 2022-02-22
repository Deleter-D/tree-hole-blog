<template>
  <div class="position">
    <el-card @click="getId" class="card">
      <el-image fit="cover"
                style="width: 100%;min-height: 100px"
                :src="article.articleCover"></el-image>
      <h3>{{ article.articleTitle }}</h3>
      <div>
        <el-button v-if="article.tag1Name!=null"
                   plain
                   color="#f39c12"
                   size="small">
          {{ article.tag1Name }}
        </el-button>
        <el-button v-if="article.tag2Name!=null"
                   plain
                   color="#f39c12"
                   size="small">
          {{ article.tag2Name }}
        </el-button>
        <el-button v-if="article.tag3Name!=null"
                   plain
                   color="#f39c12"
                   size="small">
          {{ article.tag3Name }}
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'MArticlePlate',
  props: {
    article: {},
  },
  methods: {
    getId() {
      this.$emit('goToDetail', this.article.articleId);
    },
  },
};
</script>

<style scoped>

.position {
  width: 94%;
  padding-left: 3%;
}

.card {
  background-color: rgba(255, 255, 255, 0.2);
}
</style>
