<template>
  <div>
    <br>
    <div v-for="item in articles" :key="item.articleId">
      <MArticlePlate @goToDetail="detail" :article="item"></MArticlePlate>
      <br>
    </div>
    <br>
    <div style="text-align: center">
      <el-pagination background
                     :total="theNumOfArticle"
                     :page-size="pageSize"
                     layout="prev,pager,next"
                     prev-text="&emsp;上一页&emsp;"
                     next-text="&emsp;下一页&emsp;"
                     @current-change="currentChangeHandler"></el-pagination>
    </div>
  </div>
</template>

<script>
import { get } from '../../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import MArticlePlate from '../../components/mobileComp/MArticlePlate';

export default {
  name: 'MArticleClassified',
  components: { MArticlePlate },
  data() {
    return {
      articles: [],
      theNumOfArticle: 0,
      currentPage: 1,
      pageSize: 10,
    };
  },
  methods: {
    detail(data) {
      this.$router.push(`/mobile/article/${data}`);
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
          this.articles = res.data;
          // this.$parent.$parent.setScrollTop(0);
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
