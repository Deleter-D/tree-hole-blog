import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import Management from './Management.vue';
import router from './router';
import store from './store';
import 'element-plus/dist/index.css';

createApp(Management)
  .use(store)
  .use(router)
  .use(ElementPlus)
  .mount('#management');
