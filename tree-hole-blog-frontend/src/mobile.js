import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import Mobile from './MApp.vue';
import router from './router';
import store from './store';
import 'element-plus/dist/index.css';

createApp(Mobile)
  .use(store)
  .use(router)
  .use(ElementPlus)
  .mount('#mobile');
