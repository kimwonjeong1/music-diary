import Axios from "axios";
import router from "@/router";
const API_URL = process.env.VUE_APP_LOCAL_URL

const initialState = {
  email: '',
  loginTime: null,
};

const mutations = {
  UPDATE_EMAIL(state, value) {
    state.email = value
  },
  UPDATE_LOGIN_TIME(state, value) {
    state.loginTime = value
  },
  EDIT_USER_INFO(state, value) {
    state.email = value.email
    router.push({ name: 'Mypage' })
  },
};

const actions = {
  update_user_info(params) {
      
      Axios.put(`${API_URL}user`, params, {
        headers: {
          'jwt-auth-token': sessionStorage.getItem('jwt-auth-token'),
          'user-email': sessionStorage.getItem('user-email')
        }
      })
      .then((res) => {
        alert(res.data)
      })
      .catch((err) => {
        alert("비밀번호를 확인해주세요")
        console.log(err)
      })
  }
};

export default {
  state: {
    ...initialState
  },
  mutations,
  actions,
}