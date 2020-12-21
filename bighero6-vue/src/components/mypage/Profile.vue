<template>
  <div id ="profile">
  <div class="inputForm mx-auto">
        <div class="profileImg">
          <input ref="imageInput" type="file" hidden @change="changeImage">
          <img id="profileImg" v-if="imgSrc" :src="imgSrc">
          <img id="basicImg" v-else src="../../assets/instagram/adventurealtitude.jpg" />
          

          <div class="profileButton"> 
          <button @click="updateImage"><img src="../../assets/add.png"/></button>&nbsp;
         <!--  <v-file-input hide-input></v-file-input> -->
          <button @click="removeImage"><img src="../../assets/delete.png"/></button>
          </div>
        </div>

    <v-form>
        <v-text-field
        v-model="nickname"
        :counter="10"
        label="닉네임"
        required
        ref="nickname"
      ></v-text-field>
      <v-alert type="success" v-if="(nickname==originNickname)&&(checkNickcname)">중복확인완료</v-alert>
      <v-alert type="error" @click ="validNickname(nickname)" v-if="(nickname!=originNickname)">닉네임 중복확인</v-alert>

      <v-text-field
        v-model="email"
        label="이메일"
        required
        readonly
      ></v-text-field>
    </v-form>   
         <v-btn class="mr-10" tile color="primary" dark  @click="deleteHandler">탈퇴</v-btn>
         <v-btn class="mr-10" tile color="primary" dark @click="updateHandler">수정</v-btn>
      </div>
  </div>
</template>

<script>
import config from "../../lib/FireBaseConfig";
import firebase from "firebase/app"; // 파이어베이스 import 
import "firebase/storage";
import constants from "../../lib/constants"
import axios from 'axios'

// Initialize Firebase
//firebase.initializeApp(config.apiKey);


  export default {
    data () {
      return {
        nickname : "",
        originNickname : "",
        email:"",
        imgSrc:"",
        originSrc: "",
        checkNickcname : false,
        file:""
      }
    },
    methods: {
      validNickname(nickname){
         axios
        .post(constants.baseUrl + "/account/checknickname",{ "nickname" :nickname },
         { headers : {"Authorization": "Bearer "+ this.$store.state.authToken }}
       ) // 토큰 인증을 위해 헤더에 내용 추가
        .then(( data ) => {
          console.log(data)
          if(data.data){
            alert("이미 사용된 닉네임 입니다.")
          }else{
            this.checkNickcname=true;
            this.$refs.nickname.readOnly =true;
            this.originNickname=this.nickname;
          }
          /*  if(data.status==200){
             alert(friend.nickname+"님 에게 친구 요청이 전송되었습니다. ")
             this.dialog=false;
           }else{
             alert("친구 요청 실패했습니다. 다시 시도해주세요! ")
           } */
              
        })
        .catch(function (error) {
          console.log(error);
          alert("친구 요청 실패했습니다. 다시시도해주세요! ")
        });
      
      },
       updateImage() {
      this.$refs.imageInput.click();
    },
    changeImage(e){
      if(e.target.files.length==0) // 사진 등록 안하면 그냥 종료
        return;
      this.file = e.target.files[0]; // 파일을 가져옴
      this.imgSrc = URL.createObjectURL(this.file); // 미리보기용으로 url 생성

      //var profileRef = firebase.storage().ref().child("profile/user");
      // this.imgSrc = "https://firebasestorage.googleapis.com/v0/b/music-diary-710d3.appspot.com/o/profile%2Fuser1.jpg?alt=media";
        
    },
    removeImage(){
      this.imgSrc=null;
      this.$refs.imageInput.value="";
    },
     updateHandler() {
       if(this.originNickname!=this.nickname){
          if(!this.checkNickcname){
            alert("닉네임 중복 확인해주세요!")
              return ; 
          }
       }
      // 루트 경로기준에서 profileImage이라는 폴더에 user + userid 파일명으로 이미지를 넣어라
     var profileRef = firebase.storage().ref().child("profile/"+this.$store.state.userId);
    if (!this.imgSrc || this.imgSrc==null){ // 이미지가 없는 경우
        this.imgSrc=null;
        profileRef.delete().then(function(){}).catch(function(err){console.log("해당되는 이미지가 없습니다.")})
      }
     else if(this.imgSrc!=this.originSrc){
       //profileRef.delete().then(function(){}).catch(function(err){console.log("해당되는 이미지가 없습니다.")})
       profileRef.put(this.file).then(function(snapshot){}); // 파이어베이스 스토리지에 저장
       this.imgSrc = "https://firebasestorage.googleapis.com/v0/b/music-diary-710d3.appspot.com/o/profile%2F"+this.$store.state.userId
        +"?alt=media"; 
        
       
      } 

      axios
        .put(constants.baseUrl + "/account/", {
          id : this.$store.state.userId,
          nickname: this.nickname,
          profileURL: this.imgSrc, 
          email : this.email
        },{ headers : {"Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
        .then(({ data }) => {
          this.$store.state.nickname= this.nickname;
          alert("수정 완료되었습니다.")
          location.reload();
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    deleteHandler() {
      if(this.originSrc){ // 원래 스토리지에 있던 경우에 삭제 수행
        var profileRef = firebase.storage().ref().child("profile/user"+this.$store.state.userId);
        profileRef.delete().then(function(){}).catch(function(err){console.log("해당되는 이미지가 없습니다.")});
      }

       axios
        .delete(constants.baseUrl + "/account/" + this.$store.state.userId
        ,{ headers : {'Authorization':"Bearer "+this.$store.state.authToken} })
        .then(({ data }) => {
          this.$store.commit("logout");
          this.$router.push("/");
        })
        .catch(function (error) {
          console.log(error);
        }); 
    },
    },
    created(){
     console.log(this.$store.state)
      axios
      .get(constants.baseUrl + "/account/" + this.$store.state.userId ,{ headers : {"Authorization": "Bearer "+ this.$store.state.authToken} })
      .then(({ data }) => {
        console.log(data)
        var member = data.member
        this.nickname = member.nickname;
        this.originNickname = member.nickname;
        this.email = member.email;
        this.imgSrc = member.profileURL;
        this.originSrc = member.profileURL;
      }).catch(function (error) {
          if (error.response) {
            // 요청이 이루어졌으며 서버가 2xx의 범위를 벗어나는 상태 코드로 응답했습니다.
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
          } else if (error.request) {
            // 요청이 이루어 졌으나 응답을 받지 못했습니다.
            // `error.request`는 브라우저의 XMLHttpRequest 인스턴스 또는
            // Node.js의 http.ClientRequest 인스턴스입니다.
            console.log(error.request);
          } else {
            // 오류를 발생시킨 요청을 설정하는 중에 문제가 발생했습니다.
            console.log("Error", error.message);
          }
          console.log(error.config);
        });;
    }
       
  }
</script>
<style scoped>
  *{
    font-family: 'Do Hyeon', sans-serif;
    font-size: 25px;
  }
</style>
<style>
#profile{
    padding:44px;
    text-align: center;
    /* background-color: #f7f5e0; */

}
.inputForm{
    width:550px;
}
.profileImg{
    display: inline-block;
}
.profileImg > img{
    width:300px;
    height: 300px;
    border-radius: 100%;
}

.profileButton {
    text-align: right;
}

.profileButton img{
    width:30px;
    height: 30px;
}

</style>