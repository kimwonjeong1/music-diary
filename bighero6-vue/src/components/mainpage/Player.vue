<template>

<div > 
    <youtube hidden :video-id="diaryDetail.song.youtubeId" @ended="end" ref="youtube" ></youtube>
<v-card style="text-align: center;">
      <h3 id="playing" style ="font-family: 'Do Hyeon', sans-serif;" class="pt-2"><v-icon small left>mdi-music</v-icon>{{diaryDetail.song.singer}}<v-icon small left>mdi-music</v-icon></h3>
      <h3 id="playing" style ="font-family: 'Do Hyeon', sans-serif; ">{{diaryDetail.song.title}}</h3>
      
            <v-btn outlined icon class="mx-1"  @click.native="playing ? pause() : play()" >
                <v-icon   v-if="!playing || paused" >mdi-play</v-icon>
                <v-icon  v-else >mdi-pause</v-icon>
            </v-btn>
            <v-btn outlined icon class="mx-1"  @click.native="stop()">
                <v-icon>mdi-stop</v-icon>
            </v-btn>
            <div style="width:95%;">
                <v-icon class ="ml-1" style ="float:left;" flat >mdi-volume-high</v-icon>     
                 <v-slider style ="height:35px" v-model="volume" @input="updateVolume(volume)" max="100" step="1"></v-slider>
            </div>     
  
         <!--    <v-btn outlined icon class="ma-2" :color="color" @click.native="loaded ? download() : reload()" v-if="!loaded">
                <v-icon>mdi-refresh</v-icon>
            </v-btn>
            <v-btn outlined icon class="ma-2" :color="color" @click.native="loaded ? download() : reload()" v-if="loaded && downloadable">
                <v-icon>mdi-download</v-icon>
            </v-btn> -->
            <!--  <v-progress-linear v-model="percentage" height="5" style="margin-top: 15px; margin-bottom: 15px;" @timeupdate="_handlePlayingUI" @click.native="setPosition()" ></v-progress-linear>  -->
           <!--  <p>{{ currentTime }} / {{ Duration }}</p> -->
      
    </v-card>
    
</div>
    
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
const axios = require("axios"); 
const cheerio = require("cheerio");

 
Vue.use(VueYoutube)

export default {
components: {
    VuetifyAudio: () => import('vuetify-audio'),
  },
  data () {
            return {
                videoId: '',
                firstPlay: true,
                isMuted: false,
                loaded: false,
                playing: false,
                paused: false,
                percentage: 0,
                currentTime: '00:00:00',
                audio: undefined,
                totalDuration: 0,
                volume:100
            }
        },

     props: {
          diaryDetail : Object ,
        //     flat: {
        //         type: Boolean,
        //         default: false
        //     },
        //     file: {
        //         type: String,
        //         default: null
        //     },
        //     autoPlay: {
        //         type: Boolean,
        //         default: false
        //     },
        //     ended: {
        //         type: Function,
        //         default: () => {},
        //     },
        //     canPlay: {
        //         type: Function,
        //         default: () => {},
        //     },
        //     color: {
        //         type: String,
        //         default: null
        //     },
        //     downloadable: {
        //         type: Boolean,
        //         default: false
        //     }
        },
        computed: {
             player() {
              return this.$refs.youtube.player;
            },
            Duration() {
                return this.totalDuration;
            },  
        },
   mounted () {
   this.test('https://www.youtube.com/results?search_query='+'헤이즈+비도오고그래서?1')



   },    

  methods: {
      test (url) {
  var optionAxios = {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,OPTIONS',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization, Content-Length, X-Requested-With'
        }
  }
  axios.get('https://cors-anywhere.herokuapp.com/' + url, optionAxios)
    .then((response) => {
      var htmlText = response.data;
      const $ = cheerio.load(response.data);
      var x = $('a#thumbnail');
      
      console.log($('ytd-video-renderer ytd-thumbnail')[0]);
  
    })
},
      
    updateVolume(volume){
        this.player.setVolume(volume)
    },
    end(){
        this.paused=true;
    },
    play() {
      this.player.playVideo()
       this.playing=true;
       this.paused=false;
    },
      pause() {
      this.player.pauseVideo()
      this.playing=false;
      this.paused=true;
    },
    stop() {
        this.player.stopVideo()
        this.playing=false;
    }
    ,
    mute(){
        this.isMuted=false;
    } ,
    getId (url) {
      return this.$youtube.getIdFromUrl(url)
    },
    setPosition(){
       this.playergetCurrentTime = parseInt(this.player.getDuration / 100 * this.percentage);
    },
    //  init() {
    //             this.player.addEventListener('timeupdate', this._handlePlayingUI);
    //             /* this.player.addEventListener('loadeddata', this._handleLoaded); */
    //            /*  this.player.addEventListener('pause', this._handlePlayPause);
    //             this.player.addEventListener('play', this._handlePlayPause);
    //             this.player.addEventListener('ended', this._handleEnded); */
    //         }, 
    //  _handleLoaded: function () {
    //             if (this.player) {
    //               /*   if (this.player.getDuration === Infinity) {
    //                     // Fix duration for streamed audio source or blob based
    //                     // https://stackoverflow.com/questions/38443084/how-can-i-add-predefined-length-to-audio-recorded-from-mediarecorder-in-chrome/39971175#39971175
    //                     this.player.getCurrentTime = 1e101;
    //                     this.player.ontimeupdate = () => {
    //                         this.player.ontimeupdate = () => {}
    //                         this.player.getCurrentTime = 0
    //                         this.totalDuration = parseInt(this.player.getDuration)
    //                         this.loaded = true;
    //                     }
    //                 } else */ 
    //                 {
    //                     this.player.getDuration().then(function(data){

    //                     this.totalDuration =data;
    //                     })
                       
    //                     this.loaded = true
    //                 }
    //                 if (this.autoPlay) this.player.playVideo()
    //             } else {
    //                 throw new Error('Failed to load sound file')
    //             }
    //         },
    //         _handlePlayingUI: function (e) {
    //           var ct ,gd;
    //             this.player.getCurrentTime().then(function(data){
    //              ct = parseInt(data)+1;  
    //            });
    //               this.player.getDuration().then(function(data){
    //              gd = parseInt(data)+1;  
    //            });
               
    //           this.percentage = (ct/gd) * 100
    //           this.currentTime = ct;
                
                
    //             this.playing = true
    //         },
    //         _handlePlayPause: function (e) {
    //             if (e.type === 'play' && this.firstPlay) {
    //                 // in some situations, audio.currentTime is the end one on chrome
    //                 this.audio.currentTime = 0;
    //                 if (this.firstPlay) {
    //                     this.firstPlay = false;
    //                 }
    //             }
    //             if (e.type === 'pause' && this.paused === false && this.playing === false) {
    //                 this.currentTime = '00:00:00'
    //             }
    //         },
    //         _handleEnded () {
    //             this.paused = this.playing = false;
    //         },
 
  } ,
  

}
</script>

<style>


</style>
