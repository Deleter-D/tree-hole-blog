<template>
  <div class="position">
    <el-card class="card">
      <h2>最新留言</h2>
      <div v-if="!loading">
        <div v-for="item in newestComments" :key="item.commentId">
          <el-card class="card">
            <h3>{{ item.commentName }}&emsp;说：</h3>
            {{ getSummary(item.commentContent) }}
          </el-card>
          <br>
        </div>
      </div>
      <div v-if="loading">
        <div v-for="count in 5" :key="count">
          <CommentPlateLoading :loading="loading"></CommentPlateLoading>
          <br>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { get } from '../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import CommentPlateLoading from './CommentPlateLoading';

export default {
  name: 'NewestComment',
  components: { CommentPlateLoading },
  data() {
    return {
      newestComments: [],
      loading: true,
    };
  },
  methods: {
    getSummary(content) {
      if (content.length > 50) {
        return `${content.substr(0, 52)}……`;
      }
      return content;
    },
  },
  mounted() {
    get('newestComment')
      .then((res) => {
        this.newestComments = res.data;
        setTimeout(() => {
          this.loading = false;
        }, 1000);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.position {
  width: 99%;
  padding-left: 1%;
}

.card {
  background-color: rgba(255, 255, 255, 0.2)
}
</style>
