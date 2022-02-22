<template>
  <div class="home">
    <el-row>
      <el-col :span="8">
        <ManagementHomeCard title="快捷方式">
          <span></span>
        </ManagementHomeCard>
      </el-col>
      <el-col :span="8">
        <ManagementHomeCard title="基本数据">
          <el-row>
            <el-col :span="12">
              <el-card shadow="hover" class="card">
                文章总数
                <br>
                &emsp;<span class="fontStyle">{{ theNumOfArticle }}</span>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card shadow="hover" class="card">
                推荐文章
                <br>
                &emsp;<span class="fontStyle">{{ theNumOfHotArticle }}</span>
              </el-card>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-card shadow="hover" class="card">
                标签总数
                <br>
                &emsp;<span class="fontStyle">{{ theNumOfTag }}</span>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card shadow="hover" class="card">
                留言总数
                <br>
                &emsp;<span class="fontStyle">{{ theNumOfComment }}</span>
              </el-card>
            </el-col>
          </el-row>
        </ManagementHomeCard>
      </el-col>
      <el-col :span="8">
        <ManagementHomeCard title="版本信息">
          <el-descriptions border
                           :column="1"
                           size="large">
            <el-descriptions-item>
              <template #label>
                <div>
                  <el-icon>
                    <Monitor/>
                  </el-icon>
                  当前版本
                </div>
              </template>
              v1.0.0
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div>
                  <el-icon>
                    <Monitor/>
                  </el-icon>
                  MySQL版本
                </div>
              </template>
              8.0.22
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label>
                <div>
                  <el-icon>
                    <Monitor/>
                  </el-icon>
                  gitHub
                </div>
              </template>
              url
            </el-descriptions-item>
          </el-descriptions>
        </ManagementHomeCard>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { Monitor } from '@element-plus/icons-vue';
// eslint-disable-next-line import/extensions
import ManagementHomeCard from '../../components/managementComp/ManagementHomeCard';
import { get } from '../../axiosUtils/axios_instance';

export default {
  name: 'Home',
  components: {
    ManagementHomeCard,
    Monitor,
  },
  data() {
    return {
      theNumOfArticle: 0,
      theNumOfHotArticle: 0,
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
    get('theNumOfHotArticle')
      .then((res) => {
        this.theNumOfHotArticle = res.data;
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
.home {
  width: 98%;
  padding-left: 1%;
  min-height: 90vh;
}

.fontStyle {
  font-size: 40px;
  color: #447ce4;
}

.card {
  width: 96%;
  padding-left: 2%;
}
</style>
