<template>
  <div class="ma-11">
      <h1>로그인</h1><v-divider></v-divider><br>
      <div>
        <Login/>
      </div>
  </div>
</template>

<script>
  export default {
 components: {
    Login: () => import('@/components/account/Login'),
   /*  Friend: () => import('@/components/mypage/Friend'), */
      },
    data () {
      return {
      }
    },
  }
</script>