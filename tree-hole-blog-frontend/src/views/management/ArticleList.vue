<template>
  <div class="articleList">
    <el-table :data="articles"
              border
              height="90%"
              stripe>
      <el-table-column prop="articleId" label="文章ID"></el-table-column>
      <el-table-column prop="articleTitle" label="文章标题"></el-table-column>
      <el-table-column prop="articleDate" label="发布日期"></el-table-column>
      <el-table-column prop="articleAuthor" label="文章作者"></el-table-column>
      <el-table-column label="是否为推荐文章">
        <template #default="scope">
          <el-switch v-model="scope.row.articleIsHot"
                     @change="changeHotStatement(scope.row.articleId)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small"
                     type="success"
                     plain
                     @click="detail(scope.row.articleId)">详情
          </el-button>
          <el-button size="small"
                     type="primary"
                     plain
                     @click="edit(scope.row.articleId)">编辑
          </el-button>
          <el-popconfirm title="确定删除此文章吗?"
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
import { ElMessage } from 'element-plus/lib/components';
import { get, del, patch } from '../../axiosUtils/axios_instance';

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
    detail(data) {
      this.$router.push(`article/${data}`);
    },
    currentChangeHandler(page) {
      this.currentPage = page;
      get('articleLimited', {
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
    del(index, data) {
      del(`article?articleId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.articles.splice(index, 1);
          get('theNumOfArticle')
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
          ElMessage({
            message: '删除失败',
            type: 'error',
          });
        });
    },
    changeHotStatement(id) {
      patch('/article', {
        articleId: id,
      })
        .then((res) => {
          console.log(res.data);
          ElMessage({
            message: '修改成功',
            type: 'success',
          });
        })
        .catch((err) => {
          console.log(err);
          ElMessage({
            message: '修改失败',
            type: 'error',
          });
        });
    },
  },
  mounted() {
    get('articleLimited', {
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
.articleList {
  width: 98%;
  padding-left: 1%;
  height: 92vh;
}
</style>
