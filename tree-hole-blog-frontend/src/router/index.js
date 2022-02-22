import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/home',
    redirect: '/',
    component: Home,
  },
  {
    path: '/list',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/Article'),
  },
  {
    path: '/listclassified/:tagId',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/ArticleClassified'),
  },
  {
    path: '/article/:id',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/ArticleDetail'),
  },
  {
    path: '/comment',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/Comment'),
  },
  {
    path: '/about',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/About'),
  },
  {
    path: '/management/login',
    name: 'Login',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/management/Login'),
  },
  {
    path: '/management',
    name: 'Management',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/management/ManagementHome'),
    children: [
      {
        path: '',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/Home'),
      },
      {
        path: 'list',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/ArticleList'),
      },
      {
        path: 'deletedlist',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/ArticleDeletedList'),
      },
      {
        path: 'add',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/ArticleAdd'),
      },
      {
        path: 'article/:id',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/ArticleDetail'),
      },
      {
        path: 'edit/:id',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/ArticleEdit'),
      },
      {
        path: 'taglist',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/TagList'),
      },
      {
        path: 'tagdeletedlist',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/TagDeletedList'),
      },
      {
        path: 'commentlist',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/CommentList'),
      },
      {
        path: 'commentdeletedlist',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/management/CommentDeletedList'),
      },
    ],
  },
  {
    path: '/mobile',
    name: 'Mobile',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/mobile/MobileHome'),
    children: [
      {
        path: '',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/mobile/MHome'),
      },
      {
        path: 'list',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/mobile/MArticle'),
      },
      {
        path: 'listclassified/:tagId',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/mobile/MArticleClassified'),
      },
      {
        path: 'article/:id',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/mobile/MArticleDetail'),
      },
      {
        path: 'comment',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/mobile/MComment'),
      },
      {
        path: 'about',
        // eslint-disable-next-line import/extensions
        component: () => import('../views/mobile/MAbout'),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
