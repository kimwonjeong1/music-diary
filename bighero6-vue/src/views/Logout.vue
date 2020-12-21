<template>
  <div></div>
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'
import constants from "../lib/constants"
export default {
    name: 'LogoutView',
    methods: {
        ...mapActions(['logout'])
    },
    created() {
        if(confirm("로그아웃 하시겠습니까?")){
         axios
        .post(constants.baseUrl + "/account/logout",{ "accessToken" : this.$store.state.authToken },
         { headers : {"Authorization": "Bearer "+ this.$store.state.authToken }}
       ) // 토큰 인증을 위해 헤더에 내용 추가
        .then(( data ) => {
          //console.log(data)
          if(data.status==200){
        this.logout()
          }  
        })
        .catch(function (error) {
          console.log(error) 
        });


        }else{
            this.$router.push("/")
        }
    }
}
</script>