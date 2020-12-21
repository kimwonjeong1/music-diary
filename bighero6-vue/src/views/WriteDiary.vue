<template>
  <div class="diary">
    <!------------------------------ Diary ------------------------------>
    <div class="back page"></div>
    <div class="current page">
      <div class="header">
        <v-menu
          v-model="menu"
          :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="290px"
        >
          <template v-slot:activator="{ on, attrs }">
            <p class="today">
              <v-icon style="cursor:pointer;"
                readonly
                v-bind="attrs"
                v-on="on"
              >event</v-icon>
              {{ date }}
            </p>
          </template>
          <v-date-picker v-model="date" @input="menu = false" @change="getDay()"></v-date-picker>
        </v-menu>
        <div style="text-align:left;">
          <span class="setting">날씨</span>
          <img class="settingimg" src="@/assets/img/weather/sunny.png" @click="wthrChoose(1)" v-if="wthr_sunny" />
          <img class="settingimg" src="@/assets/img/weather/sunny (1).png" v-else />
          <img class="settingimg" src="@/assets/img/weather/cloudy.png" @click="wthrChoose(2)" v-if="wthr_cloudy" />
          <img class="settingimg" src="@/assets/img/weather/cloudy (1).png" v-else />
          <img class="settingimg" src="@/assets/img/weather/cloud.png" @click="wthrChoose(3)" v-if="wthr_cloud" />
          <img class="settingimg" src="@/assets/img/weather/cloud (1).png" v-else />
          <img class="settingimg" src="@/assets/img/weather/windy.png" @click="wthrChoose(4)" v-if="wthr_windy" />
          <img class="settingimg" src="@/assets/img/weather/windy (1).png" v-else />
          <img class="settingimg" src="@/assets/img/weather/rainy.png" @click="wthrChoose(5)" v-if="wthr_rainy" />
          <img class="settingimg" src="@/assets/img/weather/rainy (1).png" v-else />
          <img class="settingimg" src="@/assets/img/weather/storm.png" @click="wthrChoose(6)" v-if="wthr_storm" />
          <img class="settingimg" src="@/assets/img/weather/storm (1).png" v-else />
          <img class="settingimg" src="@/assets/img/weather/snowing.png" @click="wthrChoose(7)" v-if="wthr_snowing" />
          <img class="settingimg" src="@/assets/img/weather/snowing (1).png" v-else />
        </div>
        <div style="text-align:left;">
          <span class="setting">기분</span>
          <img class="settingimg" src="@/assets/img/emotion/happy.png" @click="feelChoose(1)" v-if="feel_happy" />
          <img class="settingimg" src="@/assets/img/emotion/happy (1).png" v-else />
          <img class="settingimg" src="@/assets/img/emotion/excited.png" @click="feelChoose(2)" v-if="feel_excited" />
          <img class="settingimg" src="@/assets/img/emotion/excited (1).png" v-else />
          <img class="settingimg" src="@/assets/img/emotion/indifferent.png" @click="feelChoose(3)" v-if="feel_indifferent" />
          <img class="settingimg" src="@/assets/img/emotion/indifferent (1).png" v-else />
          <img class="settingimg" src="@/assets/img/emotion/sad.png" @click="feelChoose(4)" v-if="feel_sad" />
          <img class="settingimg" src="@/assets/img/emotion/sad (1).png" v-else />
          <img class="settingimg" src="@/assets/img/emotion/angry.png" @click="feelChoose(5)" v-if="feel_angry" />
          <img class="settingimg" src="@/assets/img/emotion/angry (1).png" v-else />
          <img class="settingimg" src="@/assets/img/emotion/fear.png" @click="feelChoose(6)" v-if="feel_fear" />
          <img class="settingimg" src="@/assets/img/emotion/fear (1).png" v-else />
        </div>
        <div class="ma-3">
          <input ref="imageInput" type="file" hidden @change="changeImage">
          <v-btn v-if="imgSrc" block color="secondary" dark @click="updateImage">사진 수정하기<v-icon dark right>mdi-camera-plus</v-icon></v-btn>
          <v-btn v-else block color="secondary" dark @click="updateImage">사진 등록하기<v-icon dark right>mdi-camera-plus</v-icon></v-btn>
          <v-img v-if="imgSrc" :src="imgSrc" aspect-ratio="2.0"></v-img>
          <img v-else src="@/assets/blankimage.png" style="width:376px;" />
        </div>
        <!-- 글 작성 start -->
        <v-container style="padding-top: 0px;padding-bottom: 5px;">
          <v-row>
            <v-col cols="12" style="padding-top: 0px;">
              <v-sheet elevation="3" class="pa-2">
                <v-textarea
                  v-model="diarytext"
                  :auto-grow="true"
                  :rounded="true"
                ></v-textarea>
              </v-sheet>
            </v-col>
          </v-row>
        </v-container>
        <!-- 글 작성 end -->
        <v-container class="px-3" fluid style="padding-top: 0px;">
          <v-row>
            <v-col>
              <v-switch style="margin-top: 0px;padding-top: 0px;"
                v-model="switch1"
                @change="showChoose()"
                :label="`${show}`"
              ></v-switch>
            </v-col>
            <v-col style="padding-top:7px;">
              <v-btn color="error" @click="checkHandler()" style="float:right;">저장</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
    <div class="next page"></div>
    <div style="float:right;">
      <!------------------------------ CD Player ------------------------------>
      <div class="cd-player" id="player">
        <div class="cd-player-inner">
          <div class="play-cover">

          </div>
          <div class="cd">
            <div class="album-cover spin">
              <img src="https://d3rt1990lpmkn.cloudfront.net/unbranded/d83a086148afe6539dae524607cbe4b6ccdd0360" alt="">

            </div>
            <div class="song-info">
              <h2 class="song-title"></h2>
              <h3 class="song-singer"> </h3>
              <h4 class="song-hits"></h4>
            </div>
          </div>
        </div>
      </div>
      <!------------------------------ 노래 검색 모달 버튼 ------------------------------>
      <div class="text-left">
        <v-dialog
          v-model="dialog"
          width="500"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="red lighten-2"
              dark
              v-bind="attrs"
              v-on="on"
              width="400"
            >
              노래 검색하기
            </v-btn>
          </template>

          <v-card>
            <v-card-title class="headline grey lighten-2">
              노래 검색
              <v-text-field
                class="songSearch mx-auto mb-2"
                flat
                hide-details
                label=" 노래 제목으로 검색하세요"
                prepend-inner-icon="mdi-magnify"
                solo-inverted
                v-model="songKeyword"
                @keyup="searchSong()"
              ></v-text-field>
            </v-card-title>

            <v-card-text style="padding-top:20px;" v-if="songKeyword != ''">
              <v-card light
                max-width="600"
                class="mx-auto pa-3"
              >
                <v-virtual-scroll
                  :items="songList"
                  height="400"
                  item-height="64"
                >
                  <template v-slot="{ item }">
                    <v-list-item >
                      <v-list-item-avatar>
                          <img src="@/assets/img/weather/sunny.png">
                        </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title>
                          <strong>{{ item.song_name }}</strong>
                        </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>
                  </template>
                </v-virtual-scroll>
              </v-card>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="primary"
                text
                @click="dialog = false"
              >
                닫기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>
      <!------------------------------ 노래 플레이어 ------------------------------>
      <div id="audio">
        <tape :playsong="selectedSong" />
      </div>
      <!------------------------------ 추천 노래 리스트 ------------------------------>
      <div style="margin-right:60px;margin-top:20px;">
        <v-sheet
          class="mx-auto"
          elevation="4"
          max-width="800"
        >
          <h2 style="padding-top:20px;padding-left:30px;">
            이런 노래는 어떠세요?
            <v-btn
              class="ma-2"
              :loading="loading4"
              :disabled="loading4"
              color="warning"
              @click="loader = 'loading4'"
            >
              노래 추천받기
              <template v-slot:loader>
                <span class="custom-loader">
                  <v-icon light>cached</v-icon>
                </span>
              </template>
            </v-btn>
          </h2>
          <v-slide-group
            v-model="model"
            class="pa-4"
            center-active
            show-arrows
          >
            <v-slide-item
              v-for="recosong in this.recosongs"
              :key="recosong"
              v-slot:default="{ active, toggle }"
            >
              <v-card
                :color="active ? 'primary' : 'grey lighten-1'"
                class="ma-4"
                height="200"
                width="100"
                @click="toggle"
              >
                <v-row
                  class="fill-height"
                  align="center"
                  justify="center"
                  @click="selectMusic(recosong)"
                >
                  <img src="@/assets/instagram/sunset.jpg" style="width:90px;" />
                  <div>
                    <h4><v-icon small left>mdi-music</v-icon>{{recosong.songsinger}}</h4>
                    <h4>{{recosong.songtitle}}</h4>
                    <span>{{recosong.songalbum}}</span>
                  </div>
                </v-row>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </div>
    </div>
  </div>
</template>

<script>
import config from "@/lib/FireBaseConfig";
import firebase from "firebase/app"; // 파이어베이스 import
import "firebase/storage";
import axios from 'axios'
import constants from "@/lib/constants"

// Initialize Firebase
firebase.initializeApp(config.apiKey);

export default {
  components: {
    tape: () => import('@/components/writediarypage/Tape'),
  },
  data: () => ({
    diaryId: "",
    isUpdate: false,
    date: new Date().toISOString().substr(0, 10),
    day: new Date().getDate(),
    month: new Date().getMonth() + 1,
    year: new Date().getYear() + 1900,
    menu: false,
    diarytext: "",
    switch1: true,
    show: "공개",
    model: null,
    file: 'http://www.hochmuth.com/mp3/Boccherini_Concerto_478-1.mp3',
    loader: null,
    loading4: false,
    dialog: false,
    /*날씨*/
    weather: "",
    wthr_sunny: true, wthr_cloudy: true, wthr_cloud: true, wthr_windy: true, wthr_rainy: true, wthr_storm: true, wthr_snowing: true,
    /*기분*/
    feeling: "",
    feel_happy: true, feel_excited: true, feel_indifferent: true, feel_sad: true, feel_angry: true, feel_fear: true,
    /*추천음악*/
    recosongs: [
      {
        songsinger: "헤이즈",
        songtitle: "비도 오고 그래서",
        songalbum: "1st mini album",
      },
      {
        songsinger: "방탄소년단",
        songtitle: "Dynamite",
        songalbum: "2nd album",
      },
      {
        songsinger: "소녀시대",
        songtitle: "Gee",
        songalbum: "2nd single album",
      },
      {
        songsinger: "원더걸스",
        songtitle: "Tell me",
        songalbum: "1st digital album",
      },
      {
        songsinger: "티아라",
        songtitle: "Roly Poly",
        songalbum: "3rd album",
      },
      {
        songsinger: "구구",
        songtitle: "Roly Poly",
        songalbum: "3rd album",
      },
      {
        songsinger: "하하",
        songtitle: "Roly Poly",
        songalbum: "3rd album",
      }
    ],
    selectedSong: [],
    /*검색음악*/
    songKeyword: '',
    songList: [],
    /*이미지*/
    imgSrc:"",
    file:""
  }),
  created() {
    if(this.$route.params.date) {
      this.isUpdate = true;

      this.diaryId = this.$route.params.id;
      this.date = this.$route.params.date;
      this.weather = this.$route.params.weather;
      this.feeling = this.$route.params.feel;
      this.imgSrc = this.$route.params.img;
      this.diarytext = this.$route.params.context;
      this.switch1 = this.$route.params.show;

      if(this.weather == "sunny") this.wthr_sunny = false;
      else if(this.weather == "cloudy") this.wthr_cloudy = false;
      else if(this.weather == "cloud") this.wthr_cloud = false;
      else if(this.weather == "windy") this.wthr_windy = false;
      else if(this.weather == "rainy") this.wthr_rainy = false;
      else if(this.weather == "storm") this.wthr_storm = false;
      else if(this.weather == "snowing") this.wthr_snowing = false;

      if(this.feeling == "happy") this.feel_happy = false;
      else if(this.feeling == "excited") this.feel_excited = false;
      else if(this.feeling == "indifferent") this.feel_indifferent = false;
      else if(this.feeling == "sad") this.feel_sad = false;
      else if(this.feeling == "angry") this.feel_angry = false;
      else if(this.feeling == "fear") this.feel_fear = false;

      if(this.switch1 == true) this.show = "공개";
      else if(this.switch1 == false) this.show = "비공개";
    }
  },
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]

      setTimeout(() => (this[l] = false), 3000)

      this.loader = null
    },
  },
  methods: {
    wthrChoose(num) {
      if(num == 1) {
        this.wthr_sunny = false; this.wthr_cloudy = true; this.wthr_cloud = true; this.wthr_windy = true; this.wthr_rainy = true; this.wthr_storm = true; this.wthr_snowing = true;
      } else if(num == 2) {
        this.wthr_sunny = true; this.wthr_cloudy = false; this.wthr_cloud = true; this.wthr_windy = true; this.wthr_rainy = true; this.wthr_storm = true; this.wthr_snowing = true;
      } else if(num == 3) {
        this.wthr_sunny = true; this.wthr_cloudy = true; this.wthr_cloud = false; this.wthr_windy = true; this.wthr_rainy = true; this.wthr_storm = true; this.wthr_snowing = true;
      } else if(num == 4) {
        this.wthr_sunny = true; this.wthr_cloudy = true; this.wthr_cloud = true; this.wthr_windy = false; this.wthr_rainy = true; this.wthr_storm = true; this.wthr_snowing = true;
      } else if(num == 5) {
        this.wthr_sunny = true; this.wthr_cloudy = true; this.wthr_cloud = true; this.wthr_windy = true; this.wthr_rainy = false; this.wthr_storm = true; this.wthr_snowing = true;
      } else if(num == 6) {
        this.wthr_sunny = true; this.wthr_cloudy = true; this.wthr_cloud = true; this.wthr_windy = true; this.wthr_rainy = true; this.wthr_storm = false; this.wthr_snowing = true;
      } else if(num == 7) {
        this.wthr_sunny = true; this.wthr_cloudy = true; this.wthr_cloud = true; this.wthr_windy = true; this.wthr_rainy = true; this.wthr_storm = true; this.wthr_snowing = false;
      }
    },
    feelChoose(num) {
      if(num == 1) {
        this.feel_happy = false; this.feel_excited = true; this.feel_indifferent = true; this.feel_sad = true; this.feel_angry = true; this.feel_fear = true;
      } else if(num == 2) {
        this.feel_happy = true; this.feel_excited = false; this.feel_indifferent = true; this.feel_sad = true; this.feel_angry = true; this.feel_fear = true;
      } else if(num == 3) {
        this.feel_happy = true; this.feel_excited = true; this.feel_indifferent = false; this.feel_sad = true; this.feel_angry = true; this.feel_fear = true;
      } else if(num == 4) {
        this.feel_happy = true; this.feel_excited = true; this.feel_indifferent = true; this.feel_sad = false; this.feel_angry = true; this.feel_fear = true;
      } else if(num == 5) {
        this.feel_happy = true; this.feel_excited = true; this.feel_indifferent = true; this.feel_sad = true; this.feel_angry = false; this.feel_fear = true;
      } else if(num == 6) {
        this.feel_happy = true; this.feel_excited = true; this.feel_indifferent = true; this.feel_sad = true; this.feel_angry = true; this.feel_fear = false;
      }
    },
    showChoose() {
      if(this.switch1) this.show = "공개";
      else this.show = "비공개";
    },
    getDay() {
      this.day = new Date(this.date).getDate();
      this.month = new Date(this.date).getMonth() + 1;
      this.year = new Date(this.date).getYear() + 1900;
    },
    checkHandler() {
      let err = true;
      let msg = '';

      if(!this.wthr_sunny) this.weather = "sunny";
      else if(!this.wthr_cloudy) this.weather = "cloudy";
      else if(!this.wthr_cloud) this.weather = "cloud";
      else if(!this.wthr_windy) this.weather = "windy";
      else if(!this.wthr_rainy) this.weather = "rainy";
      else if(!this.wthr_storm) this.weather = "storm";
      else if(!this.wthr_snowing) this.weather = "snowing";
      else {
        err = false;
        msg = '날씨를 선택해주세요.';
      };
      
      if(!this.feel_happy) this.feeling = "happy";
      else if(!this.feel_excited) this.feeling = "excited";
      else if(!this.feel_indifferent) this.feeling = "indifferent";
      else if(!this.feel_sad) this.feeling = "sad";
      else if(!this.feel_angry) this.feeling = "angry";
      else if(!this.feel_fear) this.feeling = "fear";
      else {
        err = false;
        msg = '기분을 선택해주세요.';
      };

      if(this.imgSrc == "") {
        err = false;
        msg = '사진을 등록해주세요.';
      }

      if(this.diarytext == "") {
        err = false;
        msg = '일기를 작성해주세요.';
      }

      if (!err) alert(msg);
      else if(this.isUpdate == true) {
        this.updateGibonHandler();
      }
			else {
				this.saveGibonHandler();
			}
    },
    saveGibonHandler(){
      var diaryImgRef = firebase.storage().ref().child("diary/"+this.date);
      diaryImgRef.put(this.file).then(function(snapshot){}); // 파이어베이스 스토리지에 저장
      this.imgSrc = "https://firebasestorage.googleapis.com/v0/b/music-diary-710d3.appspot.com/o/diary%2F"
                    + this.date + "?alt=media";

      axios
      .post(constants.baseUrl + "/diary", {
        context: this.diarytext,
        date: this.date,
        day: this.day,
        feel: this.feeling,
        //id: "string",
        img: this.imgSrc,
        month: this.month,
        show: this.switch1,
        song: {
          albumCover: "string",
          singer: "string",
          songId: "string",
          title: "string",
          youtube: "string"
        },
        userId: this.$store.state.userId,
        weather: this.weather,
        year: this.year
      },{ headers : {"Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
      .then(({ data }) => {
        alert("일기가 저장되었습니다.");
        this.$router.push("/diary");
      })
      .catch((error) => {
        console.dir(error);
      });
    },
    updateGibonHandler(){
      var diaryImgRef = firebase.storage().ref().child("diary/"+this.date);
      diaryImgRef.put(this.file).then(function(snapshot){}); // 파이어베이스 스토리지에 저장
      this.imgSrc = "https://firebasestorage.googleapis.com/v0/b/music-diary-710d3.appspot.com/o/diary%2F"
                    + this.date + "?alt=media";

      axios
      .put(constants.baseUrl + "/diary", {
        context: this.diarytext,
        date: this.date,
        day: this.day,
        feel: this.feeling,
        id: this.diaryId,
        img: this.imgSrc,
        month: this.month,
        show: this.switch1,
        song: {
          albumCover: "string",
          singer: "string",
          songId: "string",
          title: "string",
          youtube: "string"
        },
        userId: this.$store.state.userId,
        weather: this.weather,
        year: this.year
      },{ headers : {"Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
      .then(({ data }) => {
        alert("일기가 수정되었습니다.");
      })
      .catch((error) => {
        console.dir(error);
      });
    },
    searchSong() {
      axios
      .get(constants.baseUrl + "/music", { 
        headers : { "Authorization" : "Bearer " + this.$store.state.authToken } ,
        params : { keyword : this.songKeyword }
      }) // 토큰 인증을 위해 헤더에 내용 추가
      .then(({ data }) => {
        this.songList = data;
      })
      .catch(function (error) {
        console.log(error);
      });
    },
    selectMusic(selected) {
      this.selectedSong = selected;
    },
    updateImage() {
      this.$refs.imageInput.click();
    },
    changeImage(e){
      if(e.target.files.length==0) return; // 사진 등록 안하면 그냥 종료
      this.file = e.target.files[0]; // 파일을 가져옴
      this.imgSrc = URL.createObjectURL(this.file); // 미리보기용으로 url 생성
    },
  }
}
</script>

<style scoped>
.group {
  display: flex;
  flex: 1;
  justify-content: space-around;
}
.diary{
  margin-top:50px;
  position:relative;
  margin-left:100px;
  font-size:14px;
}
.page{  
  overflow:hidden;
  transform-origin: 0% 0%;
  position:absolute;
  width:400px;
  height:610px;
  box-shadow:0 0 3px 1px rgba(0,0,0,.3); 
  border-top-left-radius:2px;
  border-bottom-left-radius:2px;
  border-top-right-radius:12px;
  border-bottom-right-radius:12px; 
}
.current{
  background:white;
  z-index:10;
  background-position:-15px -13px;
  background-size:18px 18px;
  background-image: linear-gradient(45deg, rgba(0,0,0,.2) 1px,white 1px);
  transform:rotateY(8deg);
  box-shadow:0 0 3px 1px rgba(0,0,0,.3), inset 2px 0 3px -1px rgba(0,0,0,.3); 
}

.back{
  transform:rotateY(180deg);
  background:#fffdeb;
}
.next{
  background:#fffde8;
}

.title{
  border:0px;
  text-align: left;
}

/* current page */
.diary .current .header{
  margin-top:10px;
  text-align:center;
  font-family:sans-serif;
  position:relative;
  width:100%;
}
.today{
  font-weight:bold;
}
.header a{  
  position:absolute;
  color:#ff7055;
}
.yesterday{
  left:10px;
}
.tomorrow{
  right:10px;
}

.setting {
  font-size: 18px;
  padding-left:15px;
  padding-right:8px;
  vertical-align:top;
}
.settingimg {
  width:30px;
  padding-right:10px;
  cursor: pointer;
}

#player {
  position: absolute;
  right: 5%;
  top : -5px;
}

#audio {
  margin-left:50px;
  margin-top:15px;
  width:300px;
}

#playing {
  margin-top:15px;
}

.custom-loader {
  animation: loader 1s infinite;
  display: flex;
}
@-moz-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-webkit-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-o-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}

.songSearch {
  padding-left:20px;
}
</style>

<style lang="scss">
@import "@/style/cdplayer.scss";
</style>