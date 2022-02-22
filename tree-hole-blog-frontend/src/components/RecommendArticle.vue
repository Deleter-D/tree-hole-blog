<template>
  <div class="position">
    <el-card class="card">
      <h2>推荐文章</h2>
      <div v-if="!loading">
        <div v-for="item in hot_articles" :key="item.articleId">
          <ArticlePlate @goToDetail="detail" :article="item"></ArticlePlate>
          <br>
        </div>
      </div>
      <div v-if="loading">
        <div v-for="count in 5" :key="count">
          <ArticlePlateLoading :loading="loading"></ArticlePlateLoading>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
// eslint-disable-next-line import/extensions
import ArticlePlate from './ArticlePlate';
import { get } from '../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import ArticlePlateLoading from './ArticlePlateLoading';

export default {
  name: 'RecommendArticle',
  components: {
    ArticlePlateLoading,
    ArticlePlate,
  },
  data() {
    return {
      hot_articles: [],
      loading: true,
    };
  },
  methods: {
    detail(data) {
      this.$router.push(`/article/${data}`);
    },
  },
  mounted() {
    get('allRecommendArticle')
      .then((res) => {
        this.hot_articles = res.data;
        setTimeout(() => {
          this.loading = false;
        }, 1000);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.position {
  width: 99%;
}

.card {
  background-color: rgba(255, 255, 255, 0.2)
}
</style>
