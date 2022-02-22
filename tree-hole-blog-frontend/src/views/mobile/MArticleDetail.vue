<template>
  <div>
    <br>
    <el-card style="background-color: rgba(255,255,255,0.2)">
      <h2>{{ article.articleTitle }}</h2>
      <el-affix :offset="30">
        <el-button style="float:right;color: black"
                   color="#f39c12"
                   @click="this.$router.back()">返回
        </el-button>
      </el-affix>
      作者：{{ article.articleAuthor }}<br>
      发布时间：{{ format(article.articleDate) }}
      <el-divider/>
      <div>
        <editor v-model="article.articleContent"
                preview-only="true"
                preview-theme="github"
                style="background-color: rgba(255,255,255,0.07)"></editor>
      </div>
    </el-card>

  </div>
</template>

<script>
import Editor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import { formatDate } from '../../CommonUtils/dataFormat';
import { get } from '../../axiosUtils/axios_instance';

export default {
  name: 'MArticleDetail',
  components: { Editor },
  data() {
    return {
      article: {},
    };
  },
  methods: {
    format(date) {
      const formatedDate = new Date(date);
      return formatDate(formatedDate, 'yyyy年MM月dd日 hh:mm');
    },
  },
  mounted() {
    get(`/article?articleId=${this.$route.params.id}`)
      .then((res) => {
        this.article = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
</style>
