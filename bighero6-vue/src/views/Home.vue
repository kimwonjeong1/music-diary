<template>
  <div id="home">
    <div class="stage">
  <div class="desk">
    <div class="text">
     <h1>음악 메모리 <v-icon x-large>mdi-music</v-icon></h1>
     <span>음악으로 감성을 기록하다</span><br>
      <v-btn x-large color="accent" tile @click="moveLogin()" ><h2>로그인</h2></v-btn> 
    </div>
    <img class="notebook" src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/91003/notebook.svg"> 
   
    <img class="pen" src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/91003/pen.svg">
    
    <img class="pencil" src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/91003/pencil.svg">
    <img class="sharpener" src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/91003/sharpener.svg">
  </div>
</div>
  </div>
</template>

<script>
  export default {
    name: 'Home',

    components: {
      About: () => import('@/components/home/About'),
      Articles: () => import('@/components/home/Articles'),
      Banner: () => import('@/components/home/Banner'),
      Social: () => import('@/components/home/Social'),
      Subscribe: () => import('@/components/home/Subscribe'),
      Diary: () => import('@/components/home/Diary'),
    },methods : {
      moveLogin(){
        this.$router.push("/login")
      }
    },
    created(){
      if(this.$store.state.userId!=='') this.$router.push("/diary")
    }
  }
</script>
<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR:wght@500&display=swap');
#home{
  background: #fff4d3;
 
}
.text{
     position: absolute;
     top:30%;
     left: 37%;
     z-index: 10;
     animation: fly-up-twist 1s ease-in-out;
     //animation-delay: .5s;
     text-align: center;
    font-family: 'Do Hyeon', sans-serif;
    font-size: 30px;
}


  
@keyframes fly-down{
  0% {
    transform: translateY(-1000px);
  }
  100% {
    transform: translateY(0);
  }
}

@keyframes fly-up{
  0% {
    transform: translateY(1000px);
  }
  100% {
    transform: translateY(0);
  }
}

@keyframes fly-up-twist{
  0% {
    transform: translateY(1000px);
  }
  100% {
    transform: translateY(0) rotate(3deg);
  }
}

.stage{
  height: 100vh;
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: center;
}

.desk > img{
  transform: translate(1000px);
  animation-fill-mode: forwards;
}

.notebook{
  width: 600px;
  margin: 0 20px;
  animation: fly-up-twist 1s ease-in-out;
}

.pen{
  width: 30px;
  margin: 0 20px;
  animation: fly-up 1s ease-in-out;
  animation-delay: .5s;
}

.pencil{
  width: 20px;
  animation: fly-up-twist 1s ease-in-out;
  animation-delay: 1s;
}

.sharpener{
  width: 40px;
  animation: fly-up-twist 1s ease-in-out;
  animation-delay: 1.5s;
}
</style>

<style>
  
</style>
