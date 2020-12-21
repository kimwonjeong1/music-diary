<template>
<div id ="friend">
  <div id = "friendList">
      <div class="friendListTitle mt-0">
        <h1 class="friendListText">친구 목록</h1><v-icon large color ="black" class="addUserBnt ml-6" @click="dialogTrue()">mdi-account-plus</v-icon>
      </div>
      <!-- 유저 검색 모달 창 -->
      <v-dialog 
        v-model="dialog"
        max-width="600px"
        primary 
      >
        <v-card class="modal px-10">
          <v-card-title>
            추가하고 싶은 친구 닉네임으로 검색해주세요
          </v-card-title>
          <!-- <v-card-content> -->
      <v-text-field
        flat
        hide-details
        label="Search"
        prepend-inner-icon="mdi-magnify"
        solo-inverted
        v-model ="nonFrindKeyword"
        @keyup="searchNonFriend"
      ></v-text-field>
      <v-virtual-scroll class="mt-4"
        :items="nonFriendList"
        height="400"
        item-height="64"
      >
        <template v-slot="{ item }">
          <v-list-item >
            <v-list-item-avatar>
                <img v-if="item.profileURL" :src="item.profileURL">
                <v-icon v-else x-large>mdi-account</v-icon>
              </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>
                 <strong>{{ item.nickname }}</strong>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action>
              
              <v-btn v-if="checkWaitFriends(item.waitFriends)"  tile small dark class="mr-2" color="green" >요청보냄
       </v-btn>
             <v-btn v-else-if="!checkrequestFriends(item.id)"   dark tile small color="blue" class="mr-2">요청받음</v-btn>
             <v-btn v-else  tile  color="primary" @click="requestFriend(item)">친구요청</v-btn>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>

     

           
          <!-- </v-card-content> -->
          <v-card-actions>
            <v-btn style="float:right;"
              color="primary"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
        

      <v-text-field
      class="friendSearch mx-auto mb-5"
        flat
        hide-details
        label=" 친구 검색"
        prepend-inner-icon="mdi-magnify"
        solo-inverted
        v-model="friendKeyword"
        @keyup="searchFriend()"
      ></v-text-field>

    <v-card light
      max-width="600"
      class="mx-auto pa-3"
    >
      <v-virtual-scroll
        :items="friendList"
        height="400"
        item-height="64"
      >
        <template v-slot="{ item }">
          <v-list-item >
            <v-list-item-avatar>
                <img v-if="item.profileURL" :src="item.profileURL">
                <v-icon v-else x-large color="black">mdi-account</v-icon>
              </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>
                 <strong>{{ item.nickname }}</strong>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action class ="requestAccept">
              <v-tooltip top>
                <template v-slot:activator="{ on }">
              <v-icon v-on="on" class="mr-4" color="blue"  @click="visit(item)">mdi-book-open-variant</v-icon>
               </template>
              <span>방문하기</span>
              </v-tooltip>
               <v-tooltip top>
                <template v-slot:activator="{ on }">
              <v-icon v-on="on"  color="red" @click="deleteFriend(item.id)">mdi-account-remove</v-icon>
               </template>
              <span>친구삭제</span>
              </v-tooltip>
              
              
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
  </div>
  <div id="requestList">
      <h1 class="h1Text mt-4">요청 목록</h1>
      <span style="color:grey;">나에게 친구요청을 보낸 사용자 목록 입니다.</span>
       <v-card light
      max-width="600"
      class="mx-auto pa-3 mt-7"
    >
      <v-virtual-scroll
        :items="await_friends"
        height="400"
        item-height="64"
      >
        <template v-slot="{ item }">
          <v-list-item >
            <v-list-item-avatar>
                <img v-if="item.profileURL" :src="item.profileURL">
                <v-icon v-else x-large>mdi-account</v-icon>
              </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title>
                 <strong>{{ item.nickname }}</strong>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action class ="requestAccept">
              <v-btn tile small color="primary" class="mr-2" @click = "acceptRequest(item.id)">수락</v-btn>
              <v-btn tile small color="error"  @click = "refuseRequest(item.id)">거절</v-btn>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
  </div>
  <v-dialog
        v-model="dialog2"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
        scrollable
        @openModal ="dialog2=true"
      >
      <v-card tile>
          <v-toolbar
            flat
            dark
            color="primary"
          >
            <v-btn
              icon
              dark
              @click="dialog2 = false"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <div class="ml-15 modal">
             <v-avatar class="ma-7">
                <img v-if="friendVisitObject.profileURL" :src="friendVisitObject.profileURL">
                <v-icon v-else x-large>mdi-account</v-icon>
              </v-avatar> <span>&nbsp;&nbsp;&nbsp;&nbsp;{{friendVisitObject.nickname}} 's 다이어리</span>
            </div>
          </v-toolbar>
             <book :friendId="friendVisitId">
            </book> 

          <div style="flex: 1 1 auto;"></div>
        </v-card>
</v-dialog>
</div>

</template>

<script>
import axios from 'axios'
import constants from "../../lib/constants"
  export default {
    components: {
   
    book: () => import('@/components/friend/Book')
},
    data () {
      return {
        dialog2:false,
        friendList :[],
        await_friends : [],
        nonFriendList :[],
        nonFrindKeyword :'',
        friendKeyword :'',
        friendVisitId:'',
        friendVisitObject:'',
 
      dialog : false
      }
    },
    methods: {

      deleteFriend(friendId){
          if(confirm("친구 삭제 하시겠습니까?")){
                axios
        .delete(constants.baseUrl + "/friend", { headers : {"Authorization": "Bearer "+ this.$store.state.authToken} ,
        params :{ userId :this.$store.state.userId ,friendId : friendId } }) // 토큰 인증을 위해 헤더에 내용 추가
        .then(( data ) => {
           if(data.status==200){
              alert("친구 삭제가 완료되었습니다.")
              this.friendList = this.friendList.filter(x => x!==friendId)
              
           }
            
              
        })
        .catch(function (error) {
          console.log(error);
        });
          }
      },
      dialogTrue(){
        this.dialog=true;
        this.searchNonFriend();
      },
      checkrequestFriends(userId){
        var arr = this.await_friends.filter(x=> x.id===userId)
            if(arr.length!=0)
            return false

            else return true; 
      },
      checkWaitFriends(waitFriends){
          if(waitFriends == null ||waitFriends.length==0) {
            return false ; 
          }else {
              waitFriends = waitFriends.filter(x=> x==this.$store.state.userId)
              if(waitFriends) return true;
              else return false;
          }
      },
      requestFriend(friend){
    
           axios
        .post(constants.baseUrl + "/friend/request",{ "userId" :this.$store.state.userId ,"friendId" : friend.id   },
         { headers : {"Authorization": "Bearer "+ this.$store.state.authToken }}
       ) // 토큰 인증을 위해 헤더에 내용 추가
        .then(( data ) => {
           if(data.status==200){
             alert(friend.nickname+"님 에게 친구 요청이 전송되었습니다. ")
             this.dialog=false;
           }else{
             alert("친구 요청 실패했습니다. 다시 시도해주세요! ")
           }
              
        })
        .catch(function (error) {
          console.log(error);
          alert("친구 요청 실패했습니다. 다시시도해주세요! ")
        });
      },
      searchFriend(){
         axios
        .get(constants.baseUrl + "/friend", { headers : {"Authorization": "Bearer "+ this.$store.state.authToken} ,
        params :{ userId :this.$store.state.userId ,keyword : this.friendKeyword } }) // 토큰 인증을 위해 헤더에 내용 추가
        .then(({ data }) => {
  
           this.friendList = data
              
        })
        .catch(function (error) {
          console.log(error);
        });
      },
      searchNonFriend(){
         axios
        .get(constants.baseUrl + "/nonfriend", { headers : {"Authorization": "Bearer "+ this.$store.state.authToken} ,
        params :{ userId :this.$store.state.userId ,keyword : this.nonFrindKeyword } }) // 토큰 인증을 위해 헤더에 내용 추가
        .then(({ data }) => {
  
           this.nonFriendList = data.filter(item => item.id !== this.$store.state.userId)
           //this.nonFriendList = this.nonFriendList.filter(item => !item.id.includes(this.waitFriends))
          // !wordsToRemove.includes(word)
              
        })
        .catch(function (error) {
          console.log(error);
        });
      },
      acceptRequest(friend){
        axios
        .put(constants.baseUrl + "/friend/accept",{ 'friendId' : friend ,'userId' :this.$store.state.userId   },
         { headers : {"Authorization": "Bearer "+ this.$store.state.authToken }
          }
       ) // 토큰 인증을 위해 헤더에 내용 추가
        .then(( data ) => {
      
           if(data.status==200){
             alert("친구완료")
             this.await_friends = this.await_friends.filter(x=> x.id!==friend) // 요청목록에서 지움
             this.searchFriend(); // 친구목록 한번 더 부름 
            
           }else{
             alert("친구 수락에 실패했습니다. 다시 시도해주세요! ")
           }
              
        })
        .catch(function (error) {
          console.log(error);
        });
           
      },
      refuseRequest(friendId){
        if(confirm("요청을 거절 하시겠습니까?")){
                axios
        .put(constants.baseUrl + "/friend/noaccept",{ userId :this.$store.state.userId ,friendId : friendId } , { headers : {"Authorization": "Bearer "+ this.$store.state.authToken} ,
        }) // 토큰 인증을 위해 헤더에 내용 추가
        .then(( data ) => {
           if(data.status==200){
              alert("요청을 거절하였습니다.")
              this.await_friends = this.await_friends.filter(x=> x.id!==friendId) // 요청목록에서 지움
              
           }
        }).catch(function (error) {
          console.log(error);
        });
        }
      }
      ,visit(friend){
         console.log(friend);
         this.friendVisitId =friend.id;
         this.friendVisitObject = friend
         this.dialog2=true;

      
    } },
    computed: {
        items () {
        return Array.from({ length: this.length }, (k, v) => v + 1)
      }, 
      length () {
        return 30
      }, 
    },
    created() {
    // 친구목록 

       axios
        .get(constants.baseUrl + "/friend/"+this.$store.state.userId, { headers : {"Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
        .then(({ data }) => {
           this.friendList = data 
              
        })
        .catch(function (error) {
          console.log(error);
        });

      //요청목록 
         axios
        .get(constants.baseUrl + "/friend/request/list/"+this.$store.state.userId,{ headers : {"Authorization": "Bearer "+ this.$store.state.authToken} }

         ) // 토큰 인증을 위해 헤더에 내용 추가
        .then(({ data }) => {
              this.await_friends =data
              console.log( this.await_friends)
         
        })
        .catch(function (error) {
          console.log(error);
        }); 
    }
       
  }
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@500&display=swap');

  * {
   font-family: 'Do Hyeon', sans-serif;
   color: black;
   }

#friend{
    width:100%;
    display: inline-block;
 
}
#friend > div{
    width: 50%;
    height: 600px;
    text-align: center;
    padding: 30px 100px;
}
  #friendList{
    float: left;
}   
 #requestList{
    float: left;
} 
.friendSearch{
  width:70%;
}
.friendListTitle{
  display: inline-block;
}
.friendListText{
  float: left;
}
.requestAccept{
  display: inline;
}
.modal{
  text-align: center;
}
</style>