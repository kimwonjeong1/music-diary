<template>
<div id ="mypage" >
  <div  class="ma-11 px-14 pt-10">
      <h1>마이페이지</h1><v-divider></v-divider><br>
    <v-tabs 
      v-model="tab"
      background-color="primary"
      dark

    >
      <v-tab
        v-for="item in items"
        :key="item.tab"
      >
       <h2> {{ item.tab }}</h2>
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item 
        v-for="item in items"
        :key="item.tab"
      >
        <v-card flat >
        <div v-if="item.tab == '내정보'">
            <Profile/>
        </div>
        <div v-else>
            <Friend/>
        </div>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</div>
</template>

<script>
  export default {
 components: {
    Profile: () => import('@/components/mypage/Profile'),
     Friend: () => import('@/components/mypage/Friend'), 
      },
    data () {
      return {
        tab: null,
        items: [
          { tab: '내정보', content: 'Profile' },
          { tab: '친구관리', content: 'Friend' }
        ],
      }
    },
  }
</script>
<style>

@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@500&display=swap');
#mypage{
    background-repeat: no-repeat;
    background-size: cover; 
    background: url("https://static.pexels.com/photos/6663/desk-white-black-header.jpg") no-repeat center center fixed;
  background-size: cover;  
  height: 120%; 
  margin-top: -50px;
  padding: 0;
  }
 #mypage >div> h1{
    color: white;
  }
  #mypage h2 {
    font-family: 'Do Hyeon';
    font-size : 25px;
  }
</style>