import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Diary from '../views/Diary.vue'
import MyPage from '../views/MyPage.vue'
import Login from '../views/Login.vue'
import Playlist from '../views/Playlist.vue'
import Signup from '../views/SignupPage.vue'
import WriteDiary from '../views/WriteDiary.vue'
import Logout from '../views/Logout.vue'

Vue.use(Router)
export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/diary',
      name: 'diary',
      component: Diary,
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPage,
    },
    {

      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup,
    },
    {
      path: '/playlist',
      name: 'playlist',
      component: Playlist,
    },
    {
      path: '/writediary',
      name: 'writediary',
      component: WriteDiary,
    },

    {
      path: '/logout',
      name: 'logout',
      component: Logout,
    }
  ],
})
