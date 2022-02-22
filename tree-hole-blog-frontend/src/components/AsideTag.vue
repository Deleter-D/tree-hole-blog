<template>
  <div class="position">
    <el-card class="card">
      <h3 style="text-align: center">标签 | Tag</h3>
      <div v-if="!loading">
        <span v-for="item in tags" :key="item.tagId">
          <el-icon><CollectionTag/></el-icon>
          <el-button class="font" type="text" @click="classify(item.tagId)">
            #{{ item.tagName }}</el-button>&nbsp;
          <br>
        </span>
      </div>
      <div v-if="loading">
        <span v-for="count in 10" :key="count">
          <el-skeleton :loading="loading" animated>
            <template #template>
              <el-icon>
                <CollectionTag/>
              </el-icon>
              <el-skeleton-item variant="text" style="width: 5vw;height: 16px"/>
            </template>
          </el-skeleton>
          <br>
        </span>
      </div>
    </el-card>
  </div>
</template>

<script>
import { CollectionTag } from '@element-plus/icons-vue';

export default {
  name: 'AsideTag',
  components: { CollectionTag },
  data() {
    return {};
  },
  props: {
    tags: { type: Array },
    loading: { type: Boolean },
  },
  methods: {
    classify(tagId) {
      this.$router.push(`/listclassified/${tagId}`);
    },
  },
};
</script>

<style scoped>
.position {
  padding-left: 10%;
  width: 80%;
}

.font {
  color: #000000;
  font-size: 16px;
}

.card {
  background-color: rgba(255, 255, 255, 0.2)
}
</style>
