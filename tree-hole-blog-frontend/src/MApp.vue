<template>
  <div class="mobile">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'MApp',
};
</script>

<style scoped>
.mobile {
  width: 100vw;
  min-height: 100vh;
  background-image: url("assets/background.jpg");
  background-attachment: fixed;
}
</style>
