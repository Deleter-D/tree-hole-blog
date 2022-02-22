<template>
  <div class="commentList">
    <div>
      <el-button type="primary" @click="empty">清空文章回收站</el-button>
    </div>
    <el-table :data="comments"
              border
              height="90%"
              stripe>
      <el-table-column prop="commentId" label="留言ID"></el-table-column>
      <el-table-column prop="commentName" label="留言者昵称"></el-table-column>
      <el-table-column prop="commentContent" label="留言内容"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="commentDate" label="留言时间"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-popconfirm title="确定恢复此留言吗?"
                         @confirm="restore(scope.$index,scope.row.commentId)"
                         confirm-button-text="确定"
                         confirm-button-type="danger"
                         cancel-button-text="取消"
                         cancel-button-type="primary">
            <template #reference>
              <el-button size="small"
                         type="info"
                         plain>恢复
              </el-button>
            </template>
          </el-popconfirm>
          <el-popconfirm title="确定彻底此留言吗?删除后将无法找回"
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
import { ElMessage, ElMessageBox } from 'element-plus';
import { get, del } from '../../axiosUtils/axios_instance';

export default {
  name: 'CommentDeletedList',
  data() {
    return {
      comments: [],
      theNumOfComment: 0,
      pageSize: 20,
      currentPage: 1,
    };
  },
  methods: {
    restore(index, data) {
      del(`comment?commentId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.comments.splice(index, 1);
          get('theNumOfDeletedComment')
            .then((res1) => {
              this.theNumOfComment = res1.data;
            })
            .catch((err1) => {
              console.log(err1);
            });
          ElMessage({
            message: '恢复成功',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '恢复失败',
            type: 'error',
          });
        });
    },
    del(index, data) {
      del(`commentDeleted?commentId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.comments.splice(index, 1);
          get('theNumOfDeletedComment')
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
      get('commentDeletedLimited', {
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
    empty() {
      if (this.theNumOfComment > 0) {
        ElMessageBox.confirm(`是否清空${this.theNumOfComment}条留言`, '清空回收站', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        })
          .then(() => {
            del('allCommentDeleted')
              .then((res) => {
                console.log(res.data);
                this.comments.splice(0, this.theNumOfComment);
                this.theNumOfComment = 0;
                ElMessage({
                  message: '清空成功',
                  type: 'success',
                });
              })
              .catch((err) => {
                console.log(err);
                ElMessage({
                  message: '清空失败',
                  type: 'error',
                });
              });
          });
      } else {
        ElMessage({
          message: '回收站暂无数据',
          type: 'info',
        });
      }
    },
  },
  mounted() {
    get('commentDeletedLimited', {
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
    get('theNumOfDeletedComment')
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
  height: 91vh;
}
</style>
