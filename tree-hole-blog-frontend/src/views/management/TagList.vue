<template>
  <div>
    <el-row>
      <el-col :span="16">
        <div class="table">
          <el-table :data="tags"
                    border
                    height="90%"
                    stripe>
            <el-table-column prop="tagId" label="标签ID"></el-table-column>
            <el-table-column prop="tagName" label="标签名称"></el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button size="small"
                           type="primary"
                           plain
                           @click="edit(scope.$index,scope.row)">编辑
                </el-button>
                <el-popconfirm title="确定删除此标签吗?"
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
      </el-col>
      <el-col :span="8">
        <el-card class="table" shadow="never">
          <h3>添加标签</h3>
          <el-form @submit.prevent>
            <el-form-item>
              <el-input v-model="tagName"
                        label="标签名称"
                        placeholder="请输入标签名称"></el-input>
            </el-form-item>
            <el-form-item style="float: right">
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
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ElMessage, ElMessageBox } from 'element-plus';
import {
  del, get, post, put,
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
    clear() {
      this.tagName = '';
    },
    submit() {
      post('tag', {
        tagName: this.tagName,
      })
        .then((res) => {
          console.log(res.data);
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
    del(index, data) {
      del(`tag?tagId=${data}`)
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
    edit(index, data) {
      ElMessageBox.prompt('输入新的标签名称', '编辑标签', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPlaceholder: data.tagName,
      })
        .then(({ value }) => {
          put('tag', {
            tagId: data.tagId,
            tagName: value,
          })
            .then((res) => {
              console.log(res.data);
              get(`tag?tagId=${data.tagId}`)
                .then((res1) => {
                  this.tags.splice(index, 1, res1.data);
                  ElMessage({
                    message: '修改成功',
                    type: 'success',
                  });
                })
                .catch((err1) => {
                  console.log(err1);
                });
            })
            .catch((err) => {
              console.log(err);
              ElMessage({
                message: '修改失败',
                type: 'error',
              });
            });
        })
        .catch(() => {
          ElMessage({
            message: '取消修改',
            type: 'info',
          });
        });
    },
    currentChangeHandler(page) {
      this.currentPage = page;
      get('tagLimited', {
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
  },
  mounted() {
    get('tagLimited', {
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
    get('theNumOfTag')
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
  height: 92vh;
}
</style>
