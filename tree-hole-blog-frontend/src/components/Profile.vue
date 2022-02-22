<template>
  <div class="position">
    <div style="text-align: center">
      <span class="nickname">亦初</span><br>
      <span class="font">这个人很懒，什么都没有留下~</span><br>
      <span class="font">
        <el-icon><location-information/></el-icon>
        冰岛
      </span>
    </div>
    <el-divider/>
    <div style="text-align: center">
      <el-row>
        <el-col :span="8">文章</el-col>
        <el-col :span="8">标签</el-col>
        <el-col :span="8">留言</el-col>
      </el-row>
      <el-row class="number">
        <el-col :span="8">{{ theNumOfArticle }}</el-col>
        <el-col :span="8">{{ theNumOfTag }}</el-col>
        <el-col :span="8">{{ theNumOfComment }}</el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { LocationInformation } from '@element-plus/icons-vue';
import { get } from '../axiosUtils/axios_instance';

export default {
  name: 'Profile',
  components: { LocationInformation },
  data() {
    return {
      theNumOfArticle: 0,
      theNumOfTag: 0,
      theNumOfComment: 0,
    };
  },
  mounted() {
    get('theNumOfArticle')
      .then((res) => {
        this.theNumOfArticle = res.data;
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
.position {
  padding-left: 10%;
  width: 80%;
}

.nickname {
  color: #000000;
  font-size: 27px;
  font-family: 黑体;
  font-weight: bold;
}

.font {
  color: #3d3d3d;
}

.number {
  font-size: 30px;
}
</style>
