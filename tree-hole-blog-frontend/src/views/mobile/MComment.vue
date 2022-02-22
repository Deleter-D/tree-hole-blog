<template>
  <div>
    <br>
    <div v-for="item in comments" :key="item.commentId">
      <MCommentPlate :comment="item"></MCommentPlate>
      <br>
    </div>
    <el-affix :offset="80" position="bottom">
      <el-button class="button"
                 color="#f39c12"
                 circle
                 @click="addComment">
        <el-icon>
          <Plus/>
        </el-icon>
      </el-button>
    </el-affix>
    <el-drawer v-model="isShow"
               direction="btt"
               size="45%"
               title="发表留言">
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
          <!--<el-popconfirm title="确定要清空所以内容吗?"
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
                    </el-popconfirm>-->
          <el-button type="danger"
                     size="large"
                     @click="clear">清空
          </el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </div>
</template>

<script>
import { Plus } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';
import { get, post } from '../../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import MCommentPlate from '../../components/mobileComp/MCommentPlate';

export default {
  name: 'MComment',
  components: {
    Plus,
    MCommentPlate,
  },
  data() {
    return {
      comments: [],
      isShow: false,
      commentName: '',
      commentContent: '',
    };
  },
  methods: {
    addComment() {
      this.isShow = true;
    },
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
  },
  mounted() {
    get('allPassedComment')
      .then((res) => {
        this.comments = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.button {
  float: right;
  color: black;
  margin-right: 5vw;
  background-color: rgba(243, 156, 18, 0.5);
  width: 13vw;
  height: 13vw;
}
</style>
