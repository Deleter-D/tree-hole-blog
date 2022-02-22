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
                     layout="prev,pager,next"
                     prev-text="&emsp;上一页&emsp;"
                     next-text="&emsp;下一页&emsp;"
                     :default-current-page="this.$store.state.articleLastCurrentPage"
                     @current-change="currentChangeHandler"></el-pagination>
    </div>
  </div>
</template>

<script>
import { get } from '../../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import MArticlePlate from '../../components/mobileComp/MArticlePlate';

export default {
  name: 'MArticle',
  components: { MArticlePlate },
  data() {
    return {
      articles: [],
      theNumOfArticle: 0,
    };
  },
  methods: {
    detail(data) {
      this.$router.push(`/mobile/article/${data}`);
    },
    currentChangeHandler(page) {
      get(`articleAndTagName?currentPage=${page}&size=10`)
        .then((res) => {
          this.articles = res.data;
          this.$store.commit('setArticleLastCurrentPage', page);
          this.$parent.$parent.setScrollTop(0);
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
  },
};
</script>

<style scoped>

</style>
