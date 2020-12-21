<template>
<div>
  <youtube hidden  @ended="end" ref="youtube" ></youtube>  
    <v-card height="330" tile dark :class="{playlist}">
      <div v-show="isEmpty" style="text-align:center;" class="container">
        <h3>해당 플레이리스트에 </h3>
    <h3>재생목록이 없습니다 <v-icon>mdi-playlist-music-outline</v-icon></h3>
      </div>
    <v-list v-show="!isEmpty">
      <v-list-item @click="selectTrack(index,track)"
        v-for="(track, index) in playlist"
        :key="track"
        :class="[{selected: index === selectedNumber}, {even: index % 2 == 0}]"
        >
        <v-list-item-content >
          <!-- <v-img :src="albumUrl(track.id)"></v-img> -->
          
          <v-list-item-title><v-avatar class="mr-3"><v-img :src="albumUrl(track.album_id)"></v-img></v-avatar>{{ track.artist }} - {{ track.song_name }} </v-list-item-title>
            </v-list-item-content>
        <v-spacer></v-spacer>
        <!-- {{ track.howl.duration() }} -->
      </v-list-item>
    </v-list>
   
  </v-card>
  <v-card tile height="60" dark v-show="selectedTrack&&!isEmpty">
    <v-card-title>
      <h2>{{ selectedTrack.artist }} - {{ selectedTrack.song_name }}</h2>
      <v-spacer></v-spacer>
      <!-- <h3>{{trackInfo.seek | minutes}}/{{trackInfo.duration | minutes}}</h3> -->
    </v-card-title>    
  </v-card>
      <v-toolbar flat height=90 dark v-show="!isEmpty">
      <v-spacer></v-spacer>
      <v-btn outlined fab small color="light-blue" @click="prev()">
  <v-icon >skip_previous</v-icon>
</v-btn>
      <v-btn outlined fab small color="light-blue" @click="stop()">
        <v-icon>stop</v-icon>
      </v-btn>
      <v-btn outlined fab color="light-blue" @click="play()">
        <v-icon large>play_arrow</v-icon>
      </v-btn>
      <v-btn outlined fab small color="light-blue" @click="pause()">
        <v-icon>pause</v-icon>
      </v-btn>
      <v-btn outlined fab small color="light-blue" @click="next()">
  <v-icon>skip_next</v-icon>
</v-btn>
      <v-spacer></v-spacer>
    </v-toolbar>
    </div>
</template>

 
<script>
import Vue from 'vue'
import VueYoutube from 'vue-youtube'

 
Vue.use(VueYoutube)
  export default {
    props: {
      playlist: Array
    },
    watch: {
    playlist: function () {
      if(this.playlist.length==0){
             this.isEmpty = true;
      }else{
        this.isEmpty = false;
        this.selectedNumber = 0;
      this.selectedTrack = this.playlist[0];
      this.playlists = this.playlist.map(a=>a.youtubeId)
       console.log(this.playlists)
       //let result = objArray.map(a => a.foo);
       this.player.loadPlaylist(this.playlists,'playlist',0,0,'default')
       this.player.stopVideo();

      }
      
    }
    },
    mounted(){
      this.selectedNumber = 0;
      this.selectedTrack = this.playlist[0];
      this.playlists = this.playlist.map(a=>a.youtubeId)
       //console.log(this.playlists)
       //let result = objArray.map(a => a.foo);
       this.player.loadPlaylist(this.playlists,'playlist',0,0,'default')
       this.player.stopVideo();
    },
     data () {
            return {
                playlists: ['7-x3uD5z1bQ','afxLaQiLu-o','BzYnNdJhZQw'],
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
                volume:100,
                selectedTrack :'',
                selectedNumber :'',
                isEmpty : false
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
       pad(n, width) {

       n = n + '';

     return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;},
    albumUrl(url){
       //10197480
       //console.log(url)
       url = url+'';
       var number  = this.pad(url,8);
       var imgurl = 'https://cdnimg.melon.co.kr/cm/album/images/'+ number.slice(0,3)+'/'+ number.slice(3,5)+'/'+number.slice(5,8)+'/'+url+'_500.jpg';
       return imgurl;
   },
  selectTrack (index, track) {
     this.selectedTrack = track;
     this.selectedNumber= index;
     this.player.playVideoAt(index);
  },
  updateVolume(volume){
        this.player.setVolume(volume)
    },
    end(){
        this.paused=true;
    },
    play() {
      //this.player.playVideo()
       this.player.playVideoAt(this.selectedNumber)
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
    ,prev(){
        this.player.previousVideo();
        this.selectedNumber = (this.selectedNumber-1)%this.playlist.length;
        this.selectedTrack = this.playlist[this.selectedNumber];

    },
    next(){
        this.player.nextVideo();
         this.selectedNumber = (this.selectedNumber+1)%this.playlist.length;
        this.selectedTrack = this.playlist[this.selectedNumber];
    },
    mute(){
        this.isMuted=false;
    } ,
    getId (url) {
      return this.$youtube.getIdFromUrl(url)
    },
    setPosition(){
       this.playergetCurrentTime = parseInt(this.player.getDuration / 100 * this.percentage);
    },
}
  }
</script>

<style scoped>
  .selected {
    background-color:  orange !important;
  }
  .even {
    background-color: #505050
  }
  .playlist {
    overflow: auto
  }
</style>