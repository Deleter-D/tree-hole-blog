<template>
  <div>
    <el-card class="login_card position">
      <h2 style="text-align: center">登录</h2><br>
      <el-form class="form" @submit.prevent>
        <el-form-item label="用户名">
          <el-input v-model="form.userName"
                    placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密&emsp;码">
          <el-input v-model="form.password"
                    placeholder="请输入密码"
                    type="password"></el-input>
        </el-form-item>
        <br>
        <el-form-item style="float: right">
          <el-button type="primary"
                     size="large"
                     @click="login">登录
          </el-button>
          <el-button size="large"
                     @click="cancel">取消
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import { post } from '../../axiosUtils/axios_instance';

export default {
  name: 'Login',
  data() {
    return {
      form: {
        userName: '',
        password: '',
      },
    };
  },
  methods: {
    login() {
      post('/login', {
        userName: this.form.userName,
        password: this.form.password,
      })
        .then((res) => {
          console.log(res.data);
          this.$store.commit('setMsg', res.data.msg);
          this.$store.commit('setCode', res.data.code);
          this.$store.commit('setToken', res.data.token);
        })
        .then(() => {
          if (this.$store.state.code === '10000') {
            window.location.href = '/management';
          } else if (this.$store.state.code === '-1') {
            ElMessage('用户不存在');
          } else if (this.$store.state.code === '-2') {
            ElMessage('密码错误');
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    cancel() {
      this.$router.back();
    },
  },
};
</script>

<style scoped>
#login {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.login_card {
  height: 300px;
}

.position {
  left: 36%;
  right: 36%;
  top: 30%;
  position: fixed;
}

.form {
  margin-left: 20px;
  margin-right: 20px;
}
</style>
