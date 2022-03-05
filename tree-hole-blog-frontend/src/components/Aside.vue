<template>
  <div class="aside">
    <br><br>
    <div style="text-align: center">
      <el-image style="height: 50%;width: 50%"
                fit="cover"
                :src="this.$store.state.imagesBaseUrl
                      +'images/touxiang.jpg'"></el-image>
    </div>
    <br>
    <div>
      <Profile></Profile>
    </div>
    <br>
    <div>
      <AsideTag :tags="tags" :loading="loading"></AsideTag>
    </div>
<!--    <el-button @click="test" type="danger">登录后台</el-button>-->
  </div>
</template>

<script>
import { get } from '../axiosUtils/axios_instance';
// eslint-disable-next-line import/extensions
import AsideTag from './AsideTag';
// eslint-disable-next-line import/extensions
import Profile from './Profile';

export default {
  name: 'Aside',
  components: {
    Profile,
    AsideTag,
  },
  data() {
    return {
      article_count: 0,
      flag_count: 0,
      category_count: 0,
      tags: [],
      loading: true,
    };
  },
  methods: {
    test() {
      // this.$router.push('/login');
      window.location.href = '/management/login';
    },
  },
  mounted() {
    get('allTag')
      .then((res) => {
        this.tags = res.data;
        setTimeout(() => {
          this.loading = false;
        }, 2000);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style scoped>
.aside {
  width: 100%;
  min-height: 90vh;
}
</style>
