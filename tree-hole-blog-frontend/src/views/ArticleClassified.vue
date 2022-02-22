<template>
  <div style="width: 81vw">
    <br>
    <div v-for="item in articles" :key="item.articleId" ref="scroll">
      <ArticlePlate @goToDetail="detail" :article="item"></ArticlePlate>
      <br>
    </div>
    <br>
    <div style="text-align: center">
      <el-pagination background
                     layout="prev,pager,next"
                     :total="theNumOfArticle"
                     :page-size="pageSize"
                     prev-text="&emsp;上一页&emsp;"
                     next-text="&emsp;下一页&emsp;"
                     @current-change="currentChangeHandler"></el-pagination>
    </div>
  </div>
</template>

<script>
// eslint-disable-next-line import/extensions
import ArticlePlate from '../components/ArticlePlate';
import { get } from '../axiosUtils/axios_instance';

export default {
  name: 'ArticleClassified',
  components: { ArticlePlate },
  data() {
    return {
      theNumOfArticle: 0,
      articles: [],
      currentPage: 1,
      pageSize: 10,
    };
  },
  methods: {
    detail(data) {
      this.$router.push(`/article/${data}`);
    },
    currentChangeHandler(page) {
      get('theArticleAfterClassification', {
        params: {
          tagId: this.$route.params.tagId,
          currentPage: page,
          size: this.pageSize,
        },
      })
        .then((res) => {
          // eslint-disable-next-line vue/no-mutating-props
          this.articles = res.data;
          this.$store.commit('setArticleLastCurrentPage', page);
          this.$root.$refs.scroll.setScrollTop(0);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  watch: {
    $route() {
      this.articles.splice(0, this.theNumOfArticle);
      get('theArticleAfterClassification', {
        params: {
          tagId: this.$route.params.tagId,
          currentPage: this.currentPage,
          size: this.pageSize,
        },
      })
        .then((res) => {
          this.articles = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      get(`theNumOfArticleAfterClassification?tagId=${this.$route.params.tagId}`)
        .then((res) => {
          this.theNumOfArticle = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {
    get('theArticleAfterClassification', {
      params: {
        tagId: this.$route.params.tagId,
        currentPage: this.currentPage,
        size: this.pageSize,
      },
    })
      .then((res) => {
        this.articles = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    get(`theNumOfArticleAfterClassification?tagId=${this.$route.params.tagId}`)
      .then((res) => {
        this.theNumOfArticle = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>

</style>
