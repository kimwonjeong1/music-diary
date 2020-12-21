<template>
  <div>
    <v-row
      v-for="n in 1"
      :key="n"
      :class="n === 1 ? 'mb-6' : ''"
      no-gutters
    >
      <!-- 플레이어 -->
  <v-col>
    <v-content>
      <v-container>
        <systemBar></systemBar>
       <player-playlist-panel :playlist="playlist" :selectedTrack="selectedTrack"></player-playlist-panel>
      </v-container>
    </v-content>
   
        <!-- <v-card class="pa-2" tile>
          <div class="container-player">
            <div class="column add-bottom">
              <div id="mainwrap">
                <div id="nowPlay">
                  <span id="npAction">Paused...</span><span id="npTitle"></span>
                </div>
                <div id="audiowrap">
                  <div id="audio0">
                    <audio id="audio1" preload controls>Your browser does not support HTML5 Audio! 😢</audio>
                  </div>
                  <div id="tracks">
                    <a id="btnPrev">&vltri;</a><a id="btnNext">&vrtri;</a>
                  </div>
                </div>
                <div id="plwrap">
                  <ul id="plList"></ul>
                </div>
              </div>
            </div>
            <div class="column add-bottom center">
              <p>Music by <a href="http://www.mythium.net/">Mythium</a></p>
              <p>Download: <a href="https://archive.org/download/mythium/mythium_vbr_mp3.zip">zip</a> / <a href="https://archive.org/download/mythium/mythium_archive.torrent">torrent</a></p>
            </div>
          </div>
        </v-card> -->

      </v-col> 
        
      <!-- 플레이리스트 목록 -->
      <v-col >
        <v-row style="margin-left: 0px; max-width:700px;">
          <v-col style="padding-top: 0px;">
            <v-card class="pa-2" tile>
              <v-row>
                <v-col class="playlist">
                  <h2>마이 플레이리스트</h2>
                </v-col>
                <v-col class="playlist" style="text-align:right;">
                  <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn
                        class="mx-2"
                        color="indigo"
                        fab
                        dark
                        small
                        v-bind="attrs"
                        v-on="on"
                      >
                        <v-icon dark>mdi-plus</v-icon>
                      </v-btn>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">새 플레이리스트</span>
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="6" md="4">
                              <v-text-field v-model="newPlaylistName" label="플레이리스트 명*"  required></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                        <small>*필수 입력 값</small>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                        <v-btn color="blue darken-1" text @click="addPlayList()">Save</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                   <v-slide-group
            class="pa-3"
            center-active
            show-arrows
          >
            <v-slide-item
              v-for="(playlist,index) in myplaylist"
              :key="playlist"
            >
               <v-card 
                class="ma-4"
                height="200"
                width="100"
                @click="getDetail(playlist)"
                dark
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                >
                  <img :src="musicImg[(index*3)%4]" style="width:90px;" />
                  <div>
                    <h4>{{playlist.title}}</h4>
                  </div>
                </v-row>
              </v-card> 
            </v-slide-item>
          </v-slide-group>
                
                </v-col> 
              </v-row>
            </v-card>
          </v-col>
        </v-row>
         <!-- 내 플레이리스트 끝  -->
     <!-- 월별 프레이리스트 -->
        <v-row style="margin-left: 0px; max-width:700px;">
          <v-col>
             <v-card class="pa-2" tile>
              <v-row>
                <v-col class="playlist">
                  <h2>월별 플레이리스트</h2>
                </v-col>
              </v-row>
              <v-row>
                <v-col>
                  <v-slide-group
            class="pa-3"
            center-active
            show-arrows
          >
            <v-slide-item
              v-for="(monthplay, index) in monthplaylist"
              :key="index"
             
            >
               <v-card
                class="ma-4"
                height="200"
                width="100"
                @click="getMonthDetail(monthplay)" dark
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                >
                  <img :src="musicImg[index%4]" style="width:90px;" />
                  <div style="text-align:center;">
                      <h3>{{monthplay.title}}</h3>
                    <h4>플레이리스트</h4>
                  
                  </div>
                </v-row>
              </v-card> 
            </v-slide-item>
          </v-slide-group>

                  <!-- <div>
                    <v-carousel 
                      v-model="monthlyplaylistmodel"
                      :hide-delimiter-background="true"
                      :height="200"
                    >
                      <v-carousel-item
                        v-for="monthColor in monthlyplaylistColor"
                        :key="monthColor"
                      >
                        <v-sheet
                          :color="monthColor"
                          height="100%"
                          tile
                        >
                          <v-row
                            class="fill-height"
                            align="center"
                            justify="center"
                          >
                            <div>
                              <playlistcds :monthplaylist="monthplaylist"/> 
                            </div>
                          </v-row>
                        </v-sheet>
                      </v-carousel-item>
                    </v-carousel>
                  </div> -->
                </v-col>
              </v-row>
            </v-card> 
          </v-col>
        </v-row>
        <!-- 월별플레이리스트 끝  -->
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import constants from "../lib/constants"
export default {
  components: {
    playlistcds: () => import('@/components/PlaylistCDs'),
    systemBar : () => import('@/components/playlist/SystemBar'),
    PlayerPlaylistPanel : ()  => import('@/components/playlist/PlayerPlaylistPanel'),

  },
  data () {
    return {
    musicImg: [
         'http://lorempixel.com/output/nightlife-q-c-640-480-5.jpg',
        'https://firebasestorage.googleapis.com/v0/b/music-diary-710d3.appspot.com/o/banner%2FdiaryBanner.jpg?alt=media',
         'http://lorempixel.com/output/abstract-q-c-640-480-6.jpg',
         'https://firebasestorage.googleapis.com/v0/b/music-diary-710d3.appspot.com/o/banner%2Fdiary.jpg?alt=media'

      ],
      isMy : false,
      newPlaylistName :'',
      year :2020,
      monthList :[10,9,8,7,6],
      selectedTrack:'',
      dialog: false,
      myplaylistColor: [
        'yellow darken-2',
        'secondary',
        'red',
      ],
      myplaylistmodel: 0,    
      monthlyplaylistColor: [
        'red',
        'yellow darken-2',
        'secondary',
      ],
      monthlyplaylistmodel: 0,
      playlist: [
  {song_name: "Watermelon sugar", artist: "Harry Styles", youtubeId: '7-x3uD5z1bQ' ,display:true},
  {song_name: "Remember the Way", artist: "Ask Again", youtubeId: 'afxLaQiLu-o',display:true},
  {song_name: "Streets of Sant'Ivo", artist: "Ask Again", youtubeId: 'BzYnNdJhZQw',display:true},
  {song_name: "Remember the Way", artist: "Ask Again", youtubeId:'JGwWNGJdvx8',display:true},
  {song_name: "Streets of Sant'Ivo", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Remember the Way", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Streets of Sant'Ivo", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Remember the Way", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Streets of Sant'Ivo", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Remember the Way", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Streets of Sant'Ivo", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true},
  {song_name: "Remember the Way", artist: "Ask Again", youtubeId: '7-x3uD5z1bQ',display:true} ]
  , myplaylist : [],
    monthplaylist :[],

    }
  },

  mounted(){    
     var monthArray = this.monthList
      for (var i = 0; i < monthArray.length; i++) {
         var todayMonth=  monthArray[i];
         this.getmonthly(todayMonth);

      }

      console.log(this.monthplaylist)
       this.getMy();
      

 
    


  },
  methods :{
    selectTrack (track) {
  this.selectedTrack = track
},
addPlayList(){
     axios.post(constants.baseUrl + "/playlist", {
           userId : this.$store.state.userId,
           title : this.newPlaylistName
         },{ headers : { "Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
         .then(( {data} ) => {
             if(data.status ==200){
               alert("새로운 플레이리스트가 생성되었습니다.")
               this.getMy();
             }
         })
        .catch(function (error) {
           console.log(error);
         });
},
     getDetail(playlist){
          this.playlist = playlist.songs;
      
    },
    getMonthDetail(playlist){
      this.playlist = playlist.songs;
    },
    getMy(){
     axios.get(constants.baseUrl + "/playlist/"+ this.$store.state.userId,{ headers : { "Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
         .then(( {data} ) => {
               if(data.status==200){
                 this.myplaylist = data.playlists;
               }
         })
        .catch(function (error) {
           console.log(error);
         });
    },
getmonthly(todayMonth) {
       // 월별 플레이리스트 불러오기 (9,8,7,6,5)    
    axios.post(constants.baseUrl + "/playlist/month", {
           userId : this.$store.state.userId,
           month: todayMonth,
           year :  this.year
         },{ headers : { "Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
         .then(( data ) => {
              var songs = data.data.playlist;             
              var monthplayObject ={};
              monthplayObject.songs = songs;
              monthplayObject.title = todayMonth+'월';
              this.monthplaylist.push(monthplayObject)
         })
        .catch(function (error) {
           console.log(error);
         });

    }
  }
}
</script>

<style scoped>
.mb-6 {
  margin: 24px 24px;
}
.pa-2 {
  background-color: blanchedalmond;
}
.playlist {
  padding-top:0px;
  padding-bottom:0px;
}
/* Font Family
================================================== */

@import url('https://fonts.googleapis.com/css?family=Oxygen:300,400,700');


/* Global Styles
================================================== */

.container-player {
-webkit-font-smoothing:antialiased;
-webkit-text-size-adjust:100%;
background-color:#0665a2;
color:#fff;
font-size:1rem;
font-family:"Oxygen", HelveticaNeue, "Helvetica Neue", Helvetica, Arial, sans-serif;
font-weight:400;
letter-spacing:.025rem;
line-height:1.618;
padding:1rem 0;
}

*,::before,::after {
box-sizing:border-box;
}

* {
-webkit-tap-highlight-color:rgba(0,0,0,0);
-webkit-tap-highlight-color:transparent;
}


/* Setup
================================================== */

.container { position:relative; margin:0 auto; width:100%; }
.column { width:inherit; }


/* Typography / Links
================================================== */

p { color:#fff; display:block; font-size:.9rem; font-weight:400; margin:0 0 2px; }

a,a:visited { color:#8cc3e6; outline:0; text-decoration:underline; }
a:hover,a:focus { color:#bbdef5; }
p a,p a:visited { line-height:inherit; }


/* Misc.
================================================== */

.add-bottom { margin-bottom:2rem !important; }
.left { float:left; }
.right { float:right; }
.center { text-align:center; }
.hidden { display:none; }

.no-support {
margin:2rem auto;
text-align:center;
width:90%;
}


/* Audio Player Styles
================================================== */

/* audio {
display:none;
}

#audiowrap,
#plwrap {
margin:0 auto;
}

#tracks {
font-size:0;
position:relative;
text-align:center;
}

#nowPlay {
display:block;
font-size:0;
}

#nowPlay span {
display:inline-block;
font-size:1.05rem;
vertical-align:top;
}

#nowPlay span#npAction {
padding:21px;
width:30%;
}

#nowPlay span#npTitle {
padding:21px;
text-align:right;
width:70%;
}

#plList li {
cursor:pointer;
display:block;
margin:0;
padding:21px 0;
}

#plList li:hover {
background-color:rgba(0,0,0,.1);
}

.plItem {
position:relative;
}

.plTitle {
left:50px;
overflow:hidden;
position:absolute;
right:65px;
text-overflow:ellipsis;
top:0;
white-space:nowrap;
}

.plNum {
padding-left:21px;
width:25px;
}

.plLength {
padding-left:21px;
position:absolute;
right:21px;
top:0;
}

.plSel,
.plSel:hover {
background-color:rgba(0,0,0,.1);
color:#fff;
cursor:default !important;
}

#tracks a {
border-radius:3px;
color:#fff;
cursor:pointer;
display:inline-block;
font-size:2.3rem;
height:35px;
line-height:.175;
margin:0 5px 30px;
padding:10px 15px;
text-decoration:none;
transition:background .3s ease;
}

#tracks a:last-child {
margin-left:0;
}

#tracks a:hover,
#tracks a:active {
background-color:rgba(0,0,0,.1);
color:#fff;
}

#tracks a::-moz-focus-inner {
border:0;
padding:0;
}

 */
/* Plyr Overrides
================================================== */

.plyr--audio .plyr__controls {
background-color:transparent;
border:none;
color:#fff;
padding:20px 20px 20px 13px;
width:100%;
}

a.plyr__controls__item.plyr__control:hover,
.plyr--audio .plyr__controls button:hover,
.plyr--audio .plyr__controls button.tab-focus:focus,
.plyr__play-large {
background-color:rgba(0,0,0,.1);
}

.plyr__progress--played,
.plyr__volume--display {
color:rgba(0,0,0,.1);
}

.plyr--audio .plyr__progress--buffer,
.plyr--audio .plyr__volume--display {
background:rgba(0,0,0,.1);
}

.plyr--audio .plyr__progress--buffer {
color:rgba(0,0,0,.1);
}


/* Media Queries
================================================== */

@media only screen and (max-width:600px) {
    #nowPlay span#npAction { display:none; }
    #nowPlay span#npTitle { display:block; text-align:center; width:100%; }
}
</style>