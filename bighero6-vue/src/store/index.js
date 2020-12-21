import Vue from 'vue'
import Vuex from 'vuex'
import Axios from "axios";
import router from "@/router";
import VueCookies from "vue-cookies";
import constants from "../lib/constants"
import createPersistedState from "vuex-persistedstate";
import moduleName from "./test_moduleName";

Vue.use(Vuex)

// const modules = {
//   moduleName,
// };
// const plugins = [
//   createPersistedState({
//     paths: ["moduleName"],
//   }),
// ];
const API_URL = constants.baseUrl;

export default new Vuex.Store({
  state: {
    authToken: '',
    refreshToken: '',
    email: '',
    userId: '',
    drawer: false,
    items: [
      {
        text: 'Diary',
        href: 'diary',
      },
      {
        text: 'Playlist',
        href: 'playlist',
      },
      {
        text: 'MyPage',
        href: 'mypage',
      },
      {
        text: 'logout',
        href: 'logout',
      },
    ],
  },
  getters: {
    // auth
    isLoggedIn: (state) => !!state.authToken,
    // auth, articles
    config: (state) => ({
      headers: { Authorization: `Bearer ${state.authToken}`,
      },
    }),
 /*    categories: state => {
      const categories = []

      for (const article of state.articles) {
        if (
          !article.category ||
          categories.find(category => category.text === article.category)
        ) continue

        const text = article.category

         categories.push({
          text,
          href: '',
        }) 
      }

      return categories.sort().slice(0, 4)
    }, */
    //links: (state, getters) => {
      links: (state) => {
      //return state.items.concat(getters.categories)
      return state.items
    },
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token;
      VueCookies.set("auth-token", token);
    },
    UPDATE_EMAIL(state, email){
      state.email = email;
      VueCookies.set("email", email);
    },
    UPDATE_ID(state, id){
      state.userId = id;
      VueCookies.set("userId", id);
    },
    setDrawer: (state, payload) => (state.drawer = payload),
    toggleDrawer: state => (state.drawer = !state.drawer),
  },
  actions: {

    login({ commit }, loginData) {
      const params = {
        'email' : loginData.email,
        'password' : loginData.password
      }
      var JsonForm = JSON.stringify(params)
      Axios({method:'POST',url: constants.baseUrl+`/account/login`,params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        commit('SET_TOKEN', res.data.accessToken)
        commit('UPDATE_EMAIL', loginData.email)
        commit('UPDATE_ID', res.data.userId)
         
        // sessionStorage.setItem('jwt-auth-token', res.data.accessToken);
        // sessionStorage.setItem('user-email', loginData.email);
        // sessionStorage.setItem('jwt-refresh-token', res.data.refreshToken);
        // sessionStorage.setItem('userId', res.data.userId);
        console.log(res.data)
        console.log(this.state.authToken)
        this.state.userId =res.data.userId ; 
        /* this.state.authToken =  res.data.accessToken;
        this.state.email = loginData.email;
        this.state.refreshToken = res.data.refreshToken;
        console.log(this.state.authToken) */
        // console.log(sessionStorage.getItem('user-email'));
        // console.log(sessionStorage.getItem('jwt-auth-token'));
        // console.log(sessionStorage.getItem('userId'));

        alert("로그인 성공")
        
         router.push({ name: 'diary' })
         console.log(this.state.userId);
      })
      .catch(err => {
        alert(err.response.data)
      })
    },

    initSignUp({ commit }, loginData) {
      const params = {
        'email' : loginData.email,
        'password' : loginData.password
      }
      var JsonForm = JSON.stringify(params)
      Axios({method:'POST',url: constants.baseUrl+`/account/login`, params:params,data:JsonForm,headers:{'Content-Type': 'application/json; charset=utf-8'}})
      .then(res => {
        commit('SET_TOKEN', res.data.accessToken)
        commit('UPDATE_EMAIL', loginData.email)
        commit('UPDATE_ID', res.data.userId)
        this.state.userId =res.data.userId ; 

        
        // var fbpassword = res.data.fbpassword
        // // firebase 사용자 로그인
        // firebase.auth().signInWithEmailAndPassword(loginData.email, fbpassword)
        // .then(() => {
        
        // })
        // .catch(function(error) {
        //   // Handle Errors here.
        //   var errorMessage = error.message;
        //   console.log('파이어베이스 로그인 에러')
        //   console.log(errorMessage)
        //   // ...
        // })

        alert("로그인")

        router.push({ name: 'home' })
      })
      .catch(err => {
        alert(err.response.data)
      })
    },


    signup({ dispatch }, signupData) {
      var params = {
        email: signupData.email,
        nickname: signupData.nickname,
        password: signupData.password,
      };
      var JsonForm = JSON.stringify(params);
      Axios({
        method: "POST",
        url: constants.baseUrl+`/account/signup`,
        params: params,
        data: JsonForm,
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
      .then((res) => {
        console.log(params.email);
        console.log(params.nickname);
        console.log(params.password);
        //
        console.log("0");
        const loginData = {
          'email': params.email,
          'password': params.password,
        }
        console.log("1");
        dispatch("initSignUp", loginData)
        console.log("2");
        
        //
        alert(res.data.msg);
        console.log("3");
        router.push({ name: "login" });
        console.log("4");
      })
      .catch((err) => {
        alert(err.response.data);
        console.log(err);
      });
    },


    logout({ commit }) {
      commit("SET_TOKEN", ''); // state 에서도 삭제
      commit('UPDATE_EMAIL', '')
      commit('UPDATE_ID', '')
      VueCookies.remove("auth-token"); // cookie 에서는 삭제
      
      localStorage.clear();
      sessionStorage.clear();

      alert("로그아웃 되었습니다.");
      router.push({ name: "home" });
    },


  },
  plugins : [
    createPersistedState({ storage: window.sessionStorage })
    //로컬스토리지 대신 세션스토리지 사용 : 브라우저 창을 닫으면 자동으로 로그아웃됨 
  ]
})
