import { createStore } from 'vuex';

export default createStore({
  state: {
    msg: '',
    code: '',
    token: '',
    articleTitle: '',
    articleAuthor: '',
    articleContent: '',
    articleTag1: null,
    articleTag2: null,
    articleTag3: null,
    articleLastCurrentPage: 1,
    imagesBaseUrl: 'http://localhost:8081/',
  },
  mutations: {
    setMsg(state, payload) {
      state.msg = payload;
    },
    setCode(state, payload) {
      state.code = payload;
    },
    setToken(state, payload) {
      state.token = payload;
    },
    setArticleTitle(state, payload) {
      state.articleTitle = payload;
    },
    setArticleAuthor(state, payload) {
      state.articleAuthor = payload;
    },
    setArticleContent(state, payload) {
      state.articleContent = payload;
    },
    setArticleTag1(state, payload) {
      state.articleTag1 = payload;
    },
    setArticleTag2(state, payload) {
      state.articleTag2 = payload;
    },
    setArticleTag3(state, payload) {
      state.articleTag3 = payload;
    },
    setArticleLastCurrentPage(state, payload) {
      state.articleLastCurrentPage = payload;
    },
  },
  actions: {},
  modules: {},
});
