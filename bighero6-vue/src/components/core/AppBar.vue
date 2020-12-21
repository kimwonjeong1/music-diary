<template>
  <v-app-bar v-if="this.$route.name!=='home'"
    app
    flat
  >
    <v-app-bar-nav-icon
      class="hidden-md-and-up"
      @click="toggleDrawer"
    />

    <v-container class="py-0">
      <v-row align="center">
      <v-img
          :src="require('@/assets/logo.png')"
          class="mr-5"
          contain
          height="48"
          width="48"
          max-width="48"
          @click="$vuetify.goTo(0)"
        /> 

        <v-btn 
          v-for="(link, i) in links"
          :key="i"
          v-bind="link"
          class="hidden-sm-and-down"
          v-show="show(link.text)"
          text
          @click="onClick($event, link)"
        >
          {{ link.text }}
        </v-btn>
        {{this.$store.state.nickname}}
    

        <v-spacer />

        <!-- <v-text-field
          append-icon="mdi-magnify"
          flat
          hide-details
          solo-inverted
          style="max-width: 300px;"
        /> -->
      </v-row>
    </v-container>
  </v-app-bar>
</template>

<script>
  // Utilities
  import {
    mapGetters,
    mapMutations,
  } from 'vuex'

  export default {
    name: 'CoreAppBar',

    computed: {
      ...mapGetters(['links']),
    },
    data(){
   return {

   }
    },
    methods: {
      ...mapMutations(['toggleDrawer']),
      onClick (e, item) {
      e.preventDefault()
       console.log(item)
       console.log(this.$store.state.userId)
        if (this.$store.state.userId!=='') {
            return  this.$router.push("/"+item.text)
        }
        else alert("로그인이 필요한 서비스입니다")
      },
      show(text){
           if(text!=='logout') return true;
           else if (this.$store.state.userId!=='') return true;
           else return false;
      }
    },
  }
</script>
