<template>
  <div>
    <el-card class="card" v-if="loading">
      <el-row>
        <el-col :span="6">
          <el-row>
            <el-col :offset="1" :span="22">
              <el-skeleton :loading="loading" animated>
                <template #template>
                  <el-skeleton-item variant="image" style="height: 190px;width: 100%"/>
                </template>
              </el-skeleton>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="18">
          <el-skeleton :loading="loading" animated>
            <template #template>
              <el-skeleton-item variant="h2" style="width: 10vw;height: 25px"/>
              <br><br>
              <el-skeleton-item variant="button" style="width: 3vw;height: 30px"/>&emsp;
              <el-skeleton-item variant="button" style="width: 3vw;height: 30px"/>&emsp;
              <el-skeleton-item variant="button" style="width: 3vw;height: 30px"/>
            </template>
          </el-skeleton>
        </el-col>
      </el-row>
    </el-card>
    <br>
  </div>
</template>

<script>
export default {
  name: 'ArticlePlateLoading',
  props: {
    loading: { type: Boolean },
  },
};
</script>

<style scoped>
.card {
  height: 230px;
  background-color: rgba(255, 255, 255, 0.2)
}
</style>
