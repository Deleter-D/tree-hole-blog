<template>
  <div class="comment">
    <br>
    <el-row>
      <el-col :span="18">
        <div v-if="!loading">
          <div v-for="item in comments" :key="item.commentId">
            <CommentPlate :comment="item"></CommentPlate>
            <br>
          </div>
        </div>
        <div v-if="loading">
          <div v-for="count in 10" :key="count">
            <CommentPlateLoading :loading="loading"></CommentPlateLoading>
            <br>
          </div>
        </div>
        <br>
        <div style="text-align: center">
          <el-pagination background
                         layout="prev,pager,next"
                         :total="theNumOfComment"
                         prev-text="&emsp;上一页&emsp;"
                         next-text="&emsp;下一页&emsp;"
                         @current-change="currentChangeHandler"></el-pagination>
        </div>
      </el-col>
      <el-col :span="6">
        <WeeklyTopic></WeeklyTopic>
        <br>
        <div class="form">
          <el-card class="card">
            <h3>发表留言</h3>
            <el-form @submit.prevent>
              <el-form-item label="昵称">
                <el-input placeholder="请留下你的昵称"
                          show-word-limit
                          maxlength="10"
                          v-model="commentName"></el-input>
              </el-form-item>
              <el-form-item label="内容">
                <el-input placeholder="请留下友善的留言吧"
                          type="textarea"
                          rows="4"
                          show-word-limit
                          maxlength="300"
                          resize="none"
                          v-model="commentContent"></el-input>
              </el-form-item>
              <el-form-item style="float:right;">
                <el-button type="primary"
                           size="large"
                           @click="submit">发表
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
            </el-form>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-affix position="bottom" :offset="20">

    </el-affix>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus/lib/components';
// eslint-disable-next-line import/extensions
import CommentPlate from '../components/CommentPlate';
import { get, post } from '../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import WeeklyTopic from '../components/WeeklyTopic';
// eslint-disable-next-line import/extensions
import CommentPlateLoading from '../components/CommentPlateLoading';

export default {
  name: 'Comment',
  components: {
    CommentPlateLoading,
    WeeklyTopic,
    CommentPlate,
  },
  data() {
    return {
      commentName: '',
      commentContent: '',
      comments: [],
      theNumOfComment: 0,
      loading: true,
    };
  },
  methods: {
    clear() {
      this.commentName = '';
      this.commentContent = '';
    },
    submit() {
      const date = new Date();
      post('comment', {
        commentName: this.commentName,
        commentContent: this.commentContent,
        commentDate: date,
      })
        .then((res) => {
          console.log(res.data);
          this.clear();
          ElMessage({
            message: '发表成功，请等待审核',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '发表失败',
            type: 'error',
          });
        });
    },
    currentChangeHandler(page) {
      this.loading = true;
      get('commentLimited', {
        params: {
          currentPage: page,
          size: 10,
        },
      })
        .then((res) => {
          this.comments = res.data;
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
    get('commentLimited', {
      params: {
        currentPage: 1,
        size: 10,
      },
    })
      .then((res) => {
        this.comments = res.data;
        setTimeout(() => {
          this.loading = false;
        }, 1000);
      })
      .catch((err) => {
        console.log(err);
      });
    get('theNumOfComment')
      .then((res) => {
        this.theNumOfComment = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.comment {
  width: 81vw;
}

.form {
  width: 99%;
  padding-left: 1%;
}

.card {
  background-color: rgba(255, 255, 255, 0.2);
}
</style>
