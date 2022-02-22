<template>
  <div class="commentList">
    <el-table :data="comments"
              border
              height="90%"
              stripe>
      <el-table-column prop="commentId" label="留言ID"></el-table-column>
      <el-table-column prop="commentName" label="留言者昵称"></el-table-column>
      <el-table-column prop="commentContent" label="留言内容"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="commentDate" label="留言时间"></el-table-column>
      <el-table-column label="审核状态">
        <template #default="scope">
          <el-switch v-model="scope.row.commentIsPass"
                     @change="changePassStatement(scope.row.commentId)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-popconfirm title="确定删除此标签吗?"
                         @confirm="del(scope.$index,scope.row.commentId)"
                         confirm-button-text="确定"
                         confirm-button-type="danger"
                         cancel-button-text="取消"
                         cancel-button-type="primary">
            <template #reference>
              <el-button size="small"
                         type="danger"
                         plain>删除
              </el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <br>
    <div style="text-align: center">
      <el-pagination background
                     :total="theNumOfComment"
                     prev-text="&emsp;上一页&emsp;"
                     next-text="&emsp;下一页&emsp;"
                     :page-sizes="[10,20,30,50,100]"
                     :page-size="pageSize"
                     layout="sizes, prev, pager, next"
                     @current-change="currentChangeHandler"
                     @size-change="sizeChangeHandler"></el-pagination>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import { get, patch, del } from '../../axiosUtils/axios_instance';

export default {
  name: 'CommentList',
  data() {
    return {
      comments: [],
      theNumOfComment: 0,
      pageSize: 20,
      currentPage: 1,
    };
  },
  methods: {
    changePassStatement(data) {
      patch(`comment?commentId=${data}`)
        .then((res) => {
          console.log(res.data);
          ElMessage({
            message: '审核通过',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '审核失败',
            type: 'error',
          });
        });
    },
    del(index, data) {
      del(`comment?commentId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.comments.splice(index, 1);
          get('theNumOfComment')
            .then((res1) => {
              this.theNumOfComment = res1.data;
            })
            .catch((err1) => {
              console.log(err1);
            });
          ElMessage({
            message: '删除成功',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '删除失败',
            type: 'error',
          });
        });
    },
    currentChangeHandler(page) {
      this.currentPage = page;
      get('commentLimited', {
        params: {
          currentPage: page,
          size: this.pageSize,
        },
      })
        .then((res) => {
          this.comments = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    sizeChangeHandler(size) {
      this.pageSize = size;
      this.currentChangeHandler(this.currentPage);
    },
  },
  mounted() {
    get('commentLimited', {
      params: {
        currentPage: this.currentPage,
        size: this.pageSize,
      },
    })
      .then((res) => {
        this.comments = res.data;
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
.commentList {
  width: 98%;
  padding-left: 1%;
  height: 92vh;
}
</style>
