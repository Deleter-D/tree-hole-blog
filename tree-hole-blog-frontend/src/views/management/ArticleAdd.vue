<template>
  <div class="form">
    <el-card>
      <br>
      <el-form @submit.prevent>
        <el-form-item label="标题">
          <el-input v-model="articleTitle" placeholder="请输入文章标题"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="articleAuthor" placeholder="请输入作者昵称"></el-input>
        </el-form-item>
        <el-form-item label="标签">
          <el-select v-model="selectedTags"
                     multiple
                     multiple-limit="3"
                     placeholder="请选择标签(至多3项)"
                     style="width: 81vw;"
                     @change="select">
            <el-option v-for="item in tags" :key="item.tagId"
                       :label="item.tagName" :value="item.tagId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="封面">
          <input type="file" v-show="false"
                 ref="fileInput"
                 @change="selectedCover($event)">
          <el-button @click="uploadCover" type="primary">点击选择图片</el-button>
          &emsp;{{ coverName }}
        </el-form-item>
        <el-form-item label="正文">
          <editor v-model="articleContent"
                  showCodeRowNumber="true"
                  preview-theme="github"
                  @onSave="tempSave"
                  @onUploadImg="uploadImg"
                  style="height: 51vh"></editor>
        </el-form-item>
        <div style="float: right">
          <el-form-item>
            <el-button type="primary"
                       size="large"
                       @click="submit">提交
            </el-button>
            <el-popconfirm title="确定要清空所以内容吗?"
                           @confirm="clear"
                           confirm-button-text="确定"
                           confirm-button-type="danger"
                           cancel-button-text="取消"
                           cancel-button-type="primary">
              <template #reference>
                <el-button type="danger"
                           size="large">清空
                </el-button>
              </template>
            </el-popconfirm>
          </el-form-item>
        </div>
      </el-form>
      <div>

      </div>
    </el-card>
  </div>
</template>

<script>
import Editor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import { ElMessage } from 'element-plus/lib/components';
import { get, post, upload } from '../../axiosUtils/axios_instance';

export default {
  name: 'ArticleAdd',
  data() {
    return {
      articleTitle: '',
      articleAuthor: '',
      articleContent: '',
      articleCover: '',
      tags: [],
      selectedTags: [],
      articleTag1: null,
      articleTag2: null,
      articleTag3: null,
      coverName: '未选择文件',
    };
  },
  components: { Editor },
  methods: {
    tempSave() {
      this.$store.commit('setArticleTitle', this.articleTitle);
      this.$store.commit('setArticleAuthor', this.articleAuthor);
      this.$store.commit('setArticleContent', this.articleContent);
      this.$store.commit('setArticleTag1', this.articleTag1);
      this.$store.commit('setArticleTag2', this.articleTag2);
      this.$store.commit('setArticleTag3', this.articleTag3);
      ElMessage({
        message: '已保存',
        type: 'success',
      });
    },
    select(val) {
      if (val[0] !== undefined) {
        // eslint-disable-next-line prefer-destructuring
        this.articleTag1 = val[0];
      } else {
        this.articleTag1 = null;
      }
      if (val[1] !== undefined) {
        // eslint-disable-next-line prefer-destructuring
        this.articleTag2 = val[1];
      } else {
        this.articleTag2 = null;
      }
      if (val[2] !== undefined) {
        // eslint-disable-next-line prefer-destructuring
        this.articleTag3 = val[2];
      } else {
        this.articleTag3 = null;
      }
    },
    submit() {
      const date = new Date();
      const params = {
        articleTitle: this.articleTitle,
        articleAuthor: this.articleAuthor,
        articleContent: this.articleContent,
        articleCover: this.articleCover,
        articleDate: date,
      };
      if (this.articleTag1 !== null) {
        params.articleTag1 = this.articleTag1;
      }
      if (this.articleTag2 !== null) {
        params.articleTag2 = this.articleTag2;
      }
      if (this.articleTag3 !== null) {
        params.articleTag3 = this.articleTag3;
      }
      post('article', params)
        .then((res) => {
          console.log(res.data);
          this.$router.back();
          ElMessage({
            message: '添加成功',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '添加失败',
            type: 'error',
          });
        });
    },
    clear() {
      this.articleTitle = '';
      this.articleAuthor = '亦初';
      this.articleContent = '';
      this.articleCover = '';
      this.selectedTags = [];
      this.articleTag1 = null;
      this.articleTag2 = null;
      this.articleTag3 = null;
    },
    async uploadImg(files, callback) {
      const result = await Promise.all(Array.from(files)
        // eslint-disable-next-line array-callback-return
        .map((file) => new Promise((rev, rej) => {
          const form = new FormData();
          form.append('file', file);
          upload('upload', form)
            .then((res) => rev(res))
            .catch((err) => rej(err));
        })));
      callback(result.map((item) => item.data.url));
    },
    uploadCover() {
      this.$refs.fileInput.click();
    },
    selectedCover(event) {
      if (event.target.files.length !== 0) {
        this.coverName = event.target.files[0].name;
      } else {
        this.coverName = '未选择文件';
      }
      const form = new FormData();
      form.append('file', event.target.files[0]);
      upload('upload', form)
        .then((res) => {
          this.articleCover = res.data.url;
          ElMessage({
            message: '图片上传成功',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '图片上传失败',
            type: 'error',
          });
        });
    },
  },
  mounted() {
    this.articleTitle = this.$store.state.articleTitle;
    this.articleAuthor = this.$store.state.articleAuthor;
    this.articleContent = this.$store.state.articleContent;
    this.articleTag1 = this.$store.state.articleTag1;
    this.articleTag2 = this.$store.state.articleTag2;
    this.articleTag3 = this.$store.state.articleTag3;
    if (this.articleTag1 !== null) {
      this.selectedTags[0] = this.articleTag1;
    }
    if (this.articleTag2 !== null) {
      this.selectedTags[1] = this.articleTag2;
    }
    if (this.articleTag3 !== null) {
      this.selectedTags[2] = this.articleTag3;
    }
    get('allTag')
      .then((res) => {
        this.tags = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  beforeUnmount() {
    this.$store.commit('setArticleTitle', this.articleTitle);
    this.$store.commit('setArticleAuthor', this.articleAuthor);
    this.$store.commit('setArticleContent', this.articleContent);
    this.$store.commit('setArticleTag1', this.articleTag1);
    this.$store.commit('setArticleTag2', this.articleTag2);
    this.$store.commit('setArticleTag3', this.articleTag3);
    ElMessage({
      message: '已自动保存',
      type: 'success',
    });
  },
};
</script>

<style scoped>
.form {
  width: 98%;
  padding-left: 1%;
  height: 90vh;
}
</style>
