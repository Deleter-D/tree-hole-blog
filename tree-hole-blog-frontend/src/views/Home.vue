<template>
  <div class="home">
    <br>
    <div>
      <el-card style="text-align: center;background-color: rgba(255,255,255,0.2)"
               shadow="hover" :body-style="{ padding: '0px' }">
        <el-carousel height="80vh">
          <el-carousel-item>
            <el-image fit="fill"
                      :src="this.$store.state.imagesBaseUrl
                      +'images/carousel/carousel1.png'"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image fit="cover"
                      :src="this.$store.state.imagesBaseUrl
                      +'images/carousel/carousel2.png'"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image fit="cover"
                      :src="this.$store.state.imagesBaseUrl
                      +'images/carousel/carousel3.png'"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image fit="cover"
                      :src="this.$store.state.imagesBaseUrl
                      +'images/carousel/carousel5.png'"></el-image>
          </el-carousel-item>
          <el-carousel-item>
            <el-image fit="cover"
                      :src="this.$store.state.imagesBaseUrl
                      +'images/carousel/carousel4.png'"></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-card>
    </div>
    <br><br>
    <div>
      <el-row>
        <el-col :span="18">
          <RecommendArticle></RecommendArticle>
        </el-col>
        <el-col :span="6">
          <NewestComment></NewestComment>
          <br>
<!--          <Link></Link>-->
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>

// eslint-disable-next-line import/extensions
import RecommendArticle from '../components/RecommendArticle';
// eslint-disable-next-line import/extensions
// import Link from '../components/Link';
// eslint-disable-next-line import/extensions
import NewestComment from '../components/NewestComment';

export default {
  name: 'Home',
  components: {
    NewestComment,
    // Link,
    RecommendArticle,
  },
  data() {
    return {
      currentDate: new Date(),
    };
  },
};
</script>

<style scoped>
.home {
  width: 81vw;
}
</style>
