<template>
  <div class="articleList">
    <div>
      <el-button type="primary" @click="empty">清空文章回收站</el-button>
    </div>
    <el-table :data="articles"
              border
              height="90%"
              stripe>
      <el-table-column prop="articleId" label="文章ID"></el-table-column>
      <el-table-column prop="articleTitle" label="文章标题"></el-table-column>
      <el-table-column prop="articleDate" label="发布日期"></el-table-column>
      <el-table-column prop="articleAuthor" label="文章作者"></el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-popconfirm title="确定恢复此文章吗?"
                         @confirm="restore(scope.$index,scope.row.articleId)"
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
          <el-popconfirm title="确定彻底删除此文章吗?删除后将无法找回"
                         @confirm="del(scope.$index,scope.row.articleId)"
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
                     :total="theNumOfArticle"
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
import { ElMessage, ElMessageBox } from 'element-plus/lib/components';
import { get, del } from '../../axiosUtils/axios_instance';

export default {
  name: 'AritlceList',
  data() {
    return {
      articles: [],
      theNumOfArticle: 0,
      pageSize: 20,
      currentPage: 1,
    };
  },
  methods: {
    currentChangeHandler(page) {
      this.currentPage = page;
      get('articleDeletedLimited', {
        params: {
          currentPage: page,
          size: this.pageSize,
        },
      })
        .then((res) => {
          this.articles = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    sizeChangeHandler(size) {
      this.pageSize = size;
      this.currentChangeHandler(this.currentPage);
    },
    edit(data) {
      this.$router.push(`edit/${data}`);
    },
    restore(index, data) {
      del(`article?articleId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.articles.splice(index, 1);
          get('theNumOfDeletedArticle')
            .then((res1) => {
              this.theNumOfArticle = res1.data;
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
        });
    },
    del(index, data) {
      del(`articleDeleted?articleId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.articles.splice(index, 1);
          get('theNumOfDeletedArticle')
            .then((res1) => {
              this.theNumOfArticle = res1.data;
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
        });
    },
    empty() {
      if (this.theNumOfArticle > 0) {
        ElMessageBox.confirm(`是否清空${this.theNumOfArticle}篇文章？`, '清空回收站', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        })
          .then(() => {
            del('allArticleDeleted')
              .then((res) => {
                console.log(res.data);
                this.articles.splice(0, this.theNumOfArticle);
                this.theNumOfArticle = 0;
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
    get('articleDeletedLimited', {
      params: {
        currentPage: '1',
        size: this.pageSize,
      },
    })
      .then((res) => {
        this.articles = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    get('theNumOfDeletedArticle')
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
.articleList {
  width: 98%;
  padding-left: 1%;
  height: 91vh;
}
</style>
