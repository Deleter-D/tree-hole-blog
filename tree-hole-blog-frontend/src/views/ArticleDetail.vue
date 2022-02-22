<template>
  <div class="articleDetail">
    <br>
    <el-card id="contentCard"
             style="background-color: rgba(255,255,255,0.2)">
      <div>
        <span class="title">{{ article.articleTitle }}</span>
        <el-affix :offset="80">
          <el-button style="float:right;color: black"
                     color="#f39c12"
                     @click="this.$router.back()">返回
          </el-button>
        </el-affix>
        <br>
        作者：{{ article.articleAuthor }}&emsp;&emsp;
        发布时间：{{ format(article.articleDate) }}&emsp;&emsp;
        <el-button v-if="article.tag1Name!=null"
                   plain
                   size="small"
                   color="#f39c12">
          {{ article.tag1Name }}
        </el-button>
        <el-button v-if="article.tag2Name!=null"
                   plain
                   size="small"
                   color="#f39c12">
          {{ article.tag2Name }}
        </el-button>
        <el-button v-if="article.tag3Name!=null"
                   plain
                   size="small"
                   color="#f39c12">
          {{ article.tag3Name }}
        </el-button>
        <el-divider/>
      </div>
      <div>
        <editor v-model="article.articleContent"
                previewOnly="true"
                preview-theme="github"
                style="background-color: rgba(255,255,255,0.07)"></editor>
      </div>
    </el-card>
  </div>
</template>

<script>
import Editor from 'md-editor-v3';
import { get } from '../axiosUtils/axios_instance';
import 'md-editor-v3/lib/style.css';
import { formatDate } from '../CommonUtils/dataFormat';

export default {
  name: 'ArticleDetail',
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
.articleDetail {
  width: 81vw;
}

.title {
  font-size: 32px;
  font-family: 微软雅黑;
  font-weight: bold;
}
</style>
