<template>
  <div class="position">
    <br>
    <el-card class="card"
             style="text-align: center"
             shadow="hover">
      <el-carousel height="40vw">
        <el-carousel-item>
          <el-image fit="fill"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/carousel/carousel1.png'"></el-image>
        </el-carousel-item>
        <el-carousel-item>
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/carousel/carousel2.png'"></el-image>
        </el-carousel-item>
        <el-carousel-item>
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/carousel/carousel3.png'"></el-image>
        </el-carousel-item>
        <el-carousel-item>
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/carousel/carousel5.png'"></el-image>
        </el-carousel-item>
      </el-carousel>
    </el-card>
    <br>
    <MRecommendArticle></MRecommendArticle>
    <br>
    <MTag :tags="tags"></MTag>
  </div>
</template>

<script>

// eslint-disable-next-line import/extensions
import MRecommendArticle from '../../components/mobileComp/MRecommendArticle';
// eslint-disable-next-line import/extensions
import MTag from '../../components/mobileComp/MTag';
import { get } from '../../axiosUtils/axios_instance';

export default {
  name: 'MHome',
  components: {
    MTag,
    MRecommendArticle,
  },
  data() {
    return {
      tags: [],
    };
  },
  mounted() {
    get('allTag')
      .then((res) => {
        this.tags = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.position {
  width: 94vw;
  padding-left: 3vw;
}

.card {
  background-color: rgba(255, 255, 255, 0.2);
}

.carousel {
  height: 19vh;
}
</style>
