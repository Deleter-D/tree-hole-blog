<template>
  <div>
    <div class="table">
      <div>
        <el-button type="primary" @click="empty">清空标签回收站</el-button>
      </div>
      <el-table :data="tags"
                border
                stripe
                height="90%">
        <el-table-column prop="tagId" label="标签ID"></el-table-column>
        <el-table-column prop="tagName" label="标签名称"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-popconfirm title="确定恢复此标签吗?"
                           @confirm="restore(scope.$index,scope.row.tagId)"
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
            <el-popconfirm title="确定彻底删除此标签吗?删除后将无法找回"
                           @confirm="del(scope.$index,scope.row.tagId)"
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
                       :total="theNumOfTag"
                       prev-text="&emsp;上一页&emsp;"
                       next-text="&emsp;下一页&emsp;"
                       :page-sizes="[10,20,30,50,100]"
                       :page-size="pageSize"
                       layout="sizes, prev, pager, next"
                       @current-change="currentChangeHandler"
                       @size-change="sizeChangeHandler"></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import { ElMessageBox } from 'element-plus/lib/components';
import {
  del, get,
} from '../../axiosUtils/axios_instance';

export default {
  name: 'TagList',
  data() {
    return {
      tags: [],
      tagName: '',
      theNumOfTag: 0,
      pageSize: 20,
      currentPage: 1,
      editResult: {},
    };
  },
  methods: {
    restore(index, data) {
      del(`tag?tagId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.tags.splice(index, 1);
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
      console.log(index, data);
      del(`/tagDeleted?tagId=${data}`)
        .then((res) => {
          console.log(res.data);
          this.tags.splice(index, 1);
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
      get('tagDeletedLimited', {
        params: {
          currentPage: page,
          size: this.pageSize,
        },
      })
        .then((res) => {
          this.tags = res.data;
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
      if (this.theNumOfTag > 0) {
        ElMessageBox.confirm(`是否清空${this.theNumOfTag}个标签？`, '清空回收站', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        })
          .then(() => {
            del('allTagDeleted')
              .then((res) => {
                console.log(res.data);
                this.tags.splice(0, this.theNumOfTag);
                this.theNumOfTag = 0;
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
    get('tagDeletedLimited', {
      params: {
        currentPage: '1',
        size: this.pageSize,
      },
    })
      .then((res) => {
        this.tags = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    get('theNumOfDeletedTag')
      .then((res) => {
        this.theNumOfTag = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.table {
  width: 98%;
  padding-left: 1%;
  height: 91vh;
}
</style>
