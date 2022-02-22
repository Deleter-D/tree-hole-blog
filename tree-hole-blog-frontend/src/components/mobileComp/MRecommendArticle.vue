<template>
  <div>
    <el-card class="card">
      <h3>推荐文章</h3>
      <div v-for="item in hot_articles" :key="item.articleId">
        <MArticlePlate @goToDetail="detail" :article="item"></MArticlePlate>
        <br>
      </div>
    </el-card>
  </div>
</template>

<script>
import { get } from '../../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import MArticlePlate from './MArticlePlate';

export default {
  name: 'MRecommendArticle',
  components: { MArticlePlate },
  data() {
    return {
      hot_articles: [],
    };
  },
  methods: {
    detail(data) {
      this.$router.push(`/mobile/article/${data}`);
    },
  },
  mounted() {
    get('allRecommendArticle')
      .then((res) => {
        this.hot_articles = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.card {
  background-color: rgba(255, 255, 255, 0.2)
}
</style>
