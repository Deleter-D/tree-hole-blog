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
          <el-button @click="updateCover" type="primary">点击选择图片</el-button>
          &emsp;{{ coverName }}
        </el-form-item>
        <el-form-item label="正文">
          <editor v-model="articleContent"
                  showCodeRowNumber="true"
                  preview-theme="github"
                  @onSave="tempSave"
                  @onUploadImg="uploadImg"
                  style="height: 57vh"></editor>
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
            <el-button size="large"
                       @click="cancel">取消
            </el-button>
          </el-form-item>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import Editor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import { ElMessage } from 'element-plus/lib/components';
import {
  get, put, upload, post,
} from '../../axiosUtils/axios_instance';

export default {
  name: 'ArticleAdd',
  data() {
    return {
      articleTitle: '',
      articleAuthor: '',
      articleContent: '',
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
      ElMessage('编辑页面暂不支持保存进度');
    },
    select(val) {
      if (val[0] !== undefined) {
        // eslint-disable-next-line prefer-destructuring
        this.articleTag1 = val[0];
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
      console.log(`tag1:${this.articleTag1}-tag2:${this.articleTag2}-tag3:${this.articleTag3}`);
    },
    submit() {
      const params = {
        articleId: this.$route.params.id,
        articleTitle: this.articleTitle,
        articleAuthor: this.articleAuthor,
        articleContent: this.articleContent,
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
      console.log(params);
      put('/article', params)
        .then((res) => {
          console.log(res.data);
          ElMessage({
            message: '更新成功',
            type: 'success',
          });
          this.$router.back();
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '更新失败',
            type: 'error',
          });
        });
    },
    clear() {
      this.articleTitle = '';
      this.articleAuthor = '';
      this.articleContent = '';
    },
    cancel() {
      this.$router.back();
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
    updateCover() {
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
          post('articleCover', {
            articleId: this.$route.params.id,
            articleCover: res.data.url,
          })
            .then((res1) => {
              console.log(res1.data);
              ElMessage({
                message: '修改成功',
                type: 'success',
              });
            })
            .catch((err1) => {
              console.log(err1);
              ElMessage({
                message: '修改失败',
                type: 'error',
              });
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
    get(`articleById?articleId=${this.$route.params.id}`)
      .then((res) => {
        this.articleTitle = res.data.articleTitle;
        this.articleAuthor = res.data.articleAuthor;
        this.articleContent = res.data.articleContent;
        if (res.data.articleTag1 !== null) {
          this.selectedTags[0] = res.data.articleTag1;
          this.articleTag1 = res.data.articleTag1;
        }
        if (res.data.articleTag2 !== null) {
          this.selectedTags[1] = res.data.articleTag2;
          this.articleTag2 = res.data.articleTag2;
        }
        if (res.data.articleTag3 !== null) {
          this.selectedTags[2] = res.data.articleTag3;
          this.articleTag3 = res.data.articleTag3;
        }
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
.form {
  width: 98%;
  padding-left: 1%;
  height: 90vh;
}
</style>
