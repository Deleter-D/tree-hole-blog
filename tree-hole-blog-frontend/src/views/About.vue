<template>
  <div class="position">
    <br>
    <el-card class="card">
      <el-image fit="cover"
                :src="this.$store.state.imagesBaseUrl
                      +'/images/wordCloud.png'"
                style="width: 100%;height: 300px"
                v-if="!loading"></el-image>
      <el-skeleton :loading="loading" animated>
        <template #template>
          <el-skeleton-item variant="image" style="width: 100%;height: 300px"/>
        </template>
      </el-skeleton>
    </el-card>
    <br>
    <el-row>
      <el-col :span="6">
        <el-card class="plate"
                 @click="openGithub"
                 shadow="hover">
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/Github.png'"
                    class="img"
                    v-if="!loading"></el-image>
          <el-skeleton :loading="loading" animated>
            <template #template>
              <el-skeleton-item variant="image" class="img"/>
            </template>
          </el-skeleton>
          <h2>Github</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="plate"
                 @click="openNeteaseCloudMusic"
                 shadow="hover">
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/NeteaseCloudMusic.png'"
                    class="img"
                    v-if="!loading"></el-image>
          <el-skeleton :loading="loading" animated>
            <template #template>
              <el-skeleton-item variant="image" class="img"/>
            </template>
          </el-skeleton>
          <h2>网易云音乐</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="plate"
                 @click="openTuChong"
                 shadow="hover">
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/TuChong.png'"
                    class="img"
                    v-if="!loading"></el-image>
          <el-skeleton :loading="loading" animated>
            <template #template>
              <el-skeleton-item variant="image" class="img"/>
            </template>
          </el-skeleton>
          <h2>图虫</h2>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="plate"
                 @click="open500px"
                 shadow="hover">
          <el-image fit="cover"
                    :src="this.$store.state.imagesBaseUrl
                      +'/images/500px.png'"
                    class="img"
                    v-if="!loading"></el-image>
          <el-skeleton :loading="loading" animated>
            <template #template>
              <el-skeleton-item variant="image" class="img"/>
            </template>
          </el-skeleton>
          <h2>500px</h2>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: 'About',
  data() {
    return {
      loading: true,
    };
  },
  methods: {
    openGithub() {
      window.open('https://github.com/Deleter-D?tab=repositories', '_blank');
    },
    openNeteaseCloudMusic() {
      window.open('https://music.163.com/#/user/home?id=340121051', '_blank');
    },
    openTuChong() {
      window.open('https://tuchong.com/15944353/', '_blank');
    },
    open500px() {
      window.open('https://500px.com.cn/community/user-details/679046c0a4efb8131980892d3eda21805', '_blank');
    },
  },
  mounted() {
    setTimeout(() => {
      this.loading = false;
    }, 1000);
  },
};
</script>

<style scoped>
.position {
  width: 81vw;
}

.card {
  background-color: rgba(255, 255, 255, 0.2);
  text-align: center;
}

.plate {
  width: 96%;
  margin-left: 2%;
  margin-right: 2%;
  background-color: rgba(255, 255, 255, 0.2);
  text-align: center;
}

.img {
  width: 17vw;
  height: 17vw;
}
</style>
