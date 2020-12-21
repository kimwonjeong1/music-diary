<template>
  <div class="ma-11">
      <h1>회원가입</h1><v-divider></v-divider><br>
      <div>
        <Signup/>
      </div>
  </div>
</template>

<script>
  export default {
 components: {
    Signup: () => import('@/components/account/Signup'),
      },
    data () {
      return {
      }
    },
  }
</script>