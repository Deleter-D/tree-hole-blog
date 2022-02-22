<template>
  <div style="width: 81vw">
    <br>
    <div v-if="!loading">
      <div v-for="item in articles" :key="item.articleId">
        <ArticlePlate @goToDetail="detail" :article="item"></ArticlePlate>
        <br>
      </div>
    </div>
    <div v-if="loading">
      <div v-for="count in 10" :key="count">
        <ArticlePlateLoading :loading="loading"></ArticlePlateLoading>
      </div>
    </div>
    <br>
    <div style="text-align: center">
      <el-pagination background
                     layout="prev,pager,next"
                     :total="theNumOfArticle"
                     prev-text="&emsp;上一页&emsp;"
                     next-text="&emsp;下一页&emsp;"
                     :default-current-page="this.$store.state.articleLastCurrentPage"
                     @current-change="currentChangeHandler"></el-pagination>
    </div>
  </div>
</template>

<script>
// eslint-disable-next-line import/extensions
import ArticlePlate from '../components/ArticlePlate';
import { get } from '../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import ArticlePlateLoading from '../components/ArticlePlateLoading';

export default {
  name: 'Article',
  components: {
    ArticlePlateLoading,
    ArticlePlate,
  },
  data() {
    return {
      articles: [],
      theNumOfArticle: 0,
      tags: [],
      loading: true,
    };
  },
  methods: {
    detail(data) {
      this.$router.push(`/article/${data}`);
    },
    currentChangeHandler(page) {
      this.loading = true;
      get(`articleAndTagName?currentPage=${page}&size=10`)
        .then((res) => {
          this.articles = res.data;
          this.$store.commit('setArticleLastCurrentPage', page);
          this.$root.$refs.scroll.setScrollTop(0);
          setTimeout(() => {
            this.loading = false;
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {
    get(`articleAndTagName?currentPage=${this.$store.state.articleLastCurrentPage}&size=10`)
      .then((res) => {
        this.articles = res.data;
        setTimeout(() => {
          this.loading = false;
        }, 1000);
        // this.loading = false;
      })
      .catch((err) => {
        console.log(err);
      });
    get('theNumOfArticle')
      .then((res) => {
        this.theNumOfArticle = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    get('allTag')
      .then((res) => {
        this.tags = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>

</style>
