<template>

<div>
    <!-- <v-btn @click="playVideo">play</v-btn>
    <v-btn @click="pauseVideo">pause</v-btn>
    <v-btn @click="stopVideo">stop</v-btn> -->
    <youtube hidden :video-id="videoId" ref="youtube" v-on:ended="ended" @playing.native="_handlePlayingUI" ></youtube>
    <v-card elevation="0" style="text-align: center;" :flat="flat == undefined || flat == false  ? false : true">
        <v-img
          height="200px"
          contain
          :src="require('@/assets/img/tape.png')"
        >
            <v-card-text v-if="playsong.songsinger">
                <h3 id="playing" style ="font-family: 'Do Hyeon', sans-serif;margin-top:20px;">
                    <v-icon small left>mdi-music</v-icon>
                    {{playsong.songsinger}}
                    <v-icon small left>mdi-music</v-icon>
                </h3>
                <h3 id="playing" style ="font-family: 'Do Hyeon', sans-serif;">
                    {{playsong.songtitle}}
                </h3>
                <v-btn outlined icon :color="color" @click.native="playing ? pause() : play()" style="margin-right: 72px;background-color:white;">
                    <v-icon v-if="!playing || paused" >mdi-play</v-icon>
                    <v-icon v-else >mdi-pause</v-icon>
                </v-btn>
                <v-btn outlined icon :color="color" @click.native="stop()" style="background-color:white;">
                    <v-icon>mdi-stop</v-icon>
                </v-btn>
            <!--  <v-btn outlined icon class="ma-2" :color="color" @click.native="mute()" :disabled="!loaded">
                    <v-icon v-if="!isMuted">mdi-volume-high</v-icon>
                    <v-icon v-else>mdi-volume-mute</v-icon>
                </v-btn> -->
            <!--    <v-btn outlined icon class="ma-2" :color="color" @click.native="loaded ? download() : reload()" v-if="!loaded">
                    <v-icon>mdi-refresh</v-icon>
                </v-btn>
                <v-btn outlined icon class="ma-2" :color="color" @click.native="loaded ? download() : reload()" v-if="loaded && downloadable">
                    <v-icon>mdi-download</v-icon>
                </v-btn> -->
                <!--  <v-progress-linear v-model="percentage" height="5" style="margin-top: 15px; margin-bottom: 15px;" @timeupdate="_handlePlayingUI" @click.native="setPosition()" ></v-progress-linear>  -->
            <!--  <p>{{ currentTime }} / {{ Duration }}</p> -->
            </v-card-text>
            <!-- <audio id="player" ref="player" v-on:ended="ended" v-on:canplay="canPlay" :src="file"></audio> -->
        </v-img>
    </v-card>
</div>
    
</template>

<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'
 
Vue.use(VueYoutube)

export default {
  data () {
        return {
            videoId: '7-x3uD5z1bQ',
            firstPlay: true,
            isMuted: false,
            loaded: false,
            playing: false,
            paused: false,
            percentage: 0,
            currentTime: '00:00:00',
            audio: undefined,
            totalDuration: 0,
        }
    },
    props: {
        playsong: {
            type: Object,
            default: null
        },
        flat: {
            type: Boolean,
            default: false
        },
        file: {
            type: String,
            default: null
        },
        autoPlay: {
            type: Boolean,
            default: false
        },
        ended: {
            type: Function,
            default: () => {},
        },
        canPlay: {
            type: Function,
            default: () => {},
        },
        color: {
            type: String,
            default: null
        },
        downloadable: {
            type: Boolean,
            default: false
        }
    },
    computed: {
        player() {
        return this.$refs.youtube.player;
        },
        Duration() {
            return this.totalDuration;
        },
    },
    methods: {
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
        getId (url) {
        return this.$youtube.getIdFromUrl(url)
        },
        setPosition(){
        this.playergetCurrentTime = parseInt(this.player.getDuration / 100 * this.percentage);
        },
        init() {
                    this.player.addEventListener('timeupdate', this._handlePlayingUI);
                    /* this.player.addEventListener('loadeddata', this._handleLoaded); */
                /*  this.player.addEventListener('pause', this._handlePlayPause);
                    this.player.addEventListener('play', this._handlePlayPause);
                    this.player.addEventListener('ended', this._handleEnded); */
                }, 
        _handleLoaded: function () {
            if (this.player) {
            /*   if (this.player.getDuration === Infinity) {
                    // Fix duration for streamed audio source or blob based
                    // https://stackoverflow.com/questions/38443084/how-can-i-add-predefined-length-to-audio-recorded-from-mediarecorder-in-chrome/39971175#39971175
                    this.player.getCurrentTime = 1e101;
                    this.player.ontimeupdate = () => {
                        this.player.ontimeupdate = () => {}
                        this.player.getCurrentTime = 0
                        this.totalDuration = parseInt(this.player.getDuration)
                        this.loaded = true;
                    }
                } else */ 
                {
                    this.player.getDuration().then(function(data){

                    this.totalDuration =data;
                    })
                
                    this.loaded = true
                }
                if (this.autoPlay) this.player.playVideo()
            } else {
                throw new Error('Failed to load sound file')
            }
        },
        _handlePlayingUI: function (e) {
        var ct ,gd;
            this.player.getCurrentTime().then(function(data){
            ct = parseInt(data)+1;  
        });
            this.player.getDuration().then(function(data){
            gd = parseInt(data)+1;  
        });
        
        this.percentage = (ct/gd) * 100
        this.currentTime = ct;
            
            
            this.playing = true
        },
        _handlePlayPause: function (e) {
            if (e.type === 'play' && this.firstPlay) {
                // in some situations, audio.currentTime is the end one on chrome
                this.audio.currentTime = 0;
                if (this.firstPlay) {
                    this.firstPlay = false;
                }
            }
            if (e.type === 'pause' && this.paused === false && this.playing === false) {
                this.currentTime = '00:00:00'
            }
        },
        _handleEnded () {
            this.paused = this.playing = false;
        },
    mounted () {
        /* this.init(); */
    }
  }

}
</script>

<style>


</style>
