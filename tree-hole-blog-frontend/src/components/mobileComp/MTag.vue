<template>
  <div>
    <el-card class="card">
      <h3>标签|Tag</h3>
      <span v-for="item in tags" :key="item.tagId">
        <el-icon><CollectionTag/></el-icon>
        <el-button class="font" type="text" @click="classify(item.tagId)">
          #{{ item.tagName }}</el-button>&nbsp;
        <br>
      </span>
    </el-card>
  </div>
</template>

<script>
import { CollectionTag } from '@element-plus/icons-vue';

export default {
  name: 'MTag',
  components: {
    CollectionTag,
  },
  props: {
    tags: { type: Array },
  },
  methods: {
    classify(tagId) {
      this.$router.push(`/mobile/listclassified/${tagId}`);
    },
  },
};
</script>

<style scoped>
.card {
  background-color: rgba(255, 255, 255, 0.2)
}

.font {
  color: #000000;
  font-size: 16px;
}
</style>
