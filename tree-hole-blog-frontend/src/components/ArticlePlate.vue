<template>
  <div>
    <el-card @click="getId" shadow="hover" class="card">
      <el-row>
        <el-col :span="6">
          <el-row>
            <el-col :offset="1" :span="22">
              <el-image fit="contain"
                        style="height: 190px;width: 100%"
                        :src="article.articleCover"></el-image>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="18">
          <h2>{{ article.articleTitle }}</h2>
          <div>
            <el-button v-if="article.tag1Name!=null"
                       plain
                       color="#f39c12">
              {{ article.tag1Name }}
            </el-button>
            <el-button v-if="article.tag2Name!=null"
                       plain
                       color="#f39c12">
              {{ article.tag2Name }}
            </el-button>
            <el-button v-if="article.tag3Name!=null"
                       plain
                       color="#f39c12">
              {{ article.tag3Name }}
            </el-button>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>

export default {
  name: 'ArticlePlate',
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
.card {
  height: 230px;
  background-color: rgba(255, 255, 255, 0.2)
}
</style>
