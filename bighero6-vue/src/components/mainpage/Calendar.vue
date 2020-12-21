<template>
  <div class="page-container">
    <!-- <h2 @click="changeDate">Has month and week two mode. And you can custom all style</h2> -->
    <div class="container">
      <Calendar
        class="ui-calendar"
        :dateData="dateData"
        @onMonthChange="onMonthChange"
        :mode="mode"
        @next="onNext"
        @prev="onPrev"
       
        ref="calendar"
      >
        <!-- <div slot="header-left" class="ui-calendar-header__left">
          <button
            :class="['ui-calendar-modeBtn' ,{ active: mode === 'month' }]"
            @click="mode = 'month'"
          >
            Month
          </button>
          <button
            :class="['ui-calendar-modeBtn', { active: mode === 'week' }]"
            @click="mode = 'week'"
          >
            Week
          </button>
        </div> -->
        <template v-slot:body="{ data }">
          <transition :name="transitionName">
            <div class="calendar-body-grid" :key="indentifier">
              <div v-for="(row, index) in data"
                :key="index"
                class="calendar-body-row" v-show="index<=4">
                <div v-for="col in row" 
                  class="calendar-day-item"
                  
                  :key="col.date.full" @click ="getDiaryDetail(col.date.full)">
                  <div
                    :class="[
                      'ui-calendar-item',
                      {
                        'is-otherMonth': col.isPrevMonth || col.isNextMonth,
                        'is-today': col.isToday
                      },
                    ]">
                    <div :slot="col.now" class="ui-calendar-item-date">
                      {{col.date.date}}
                    </div>
                    <div
                      class="ui-calendar-item-name"
                      v-for="(item, index) in col.data"
                      :key="index">

                      <img  class="weatherIcon mr-2" :src= "weather(item.weather)">
                      <img  class="weatherIcon" :src= "emotion(item.feel)">
                      <br>
                      <img class="albumIcon" :src="albumUrl(item.song.album_id)"><br>
                      <span>{{item.song.artist}}-{{item.song.song_name}}</span>
                    </div>
                  </div>
                </div>
                  </div>
            
            </div>
          </transition>
        </template>
      </Calendar>
    </div>
  </div>
</template>

<script>
import 'vue2-event-calendar/dist/vue2-event-calendar.css'
import { Calendar } from 'vue2-event-calendar'
import data from './data'
import axios from 'axios'
import constants from "../../lib/constants"
export default {
  components: {
    Calendar
  },
  data() {
    return {
      todayMonth:'',
      indentifier: '',
      //dateData : data().Array
      dateData: [],
      transitionName: 'slide-left',
      mode: 'month',
      diary :{},
    };
  },
  computed :{
       /*  weather : function(){
          return "../../assets/weather/"
          +this.item.weather+".png"; 
     */
  },
  methods: {
    pad(n, width) {

       n = n + '';

     return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;

   },
    deleteDiary(id){
      this.dateData = this.dateData.filter(item => item.id !== id);

    },
    weather(w) {
          return require('../../assets/img/weather/'+w+' (1).png'); 
      }
      ,
       emotion(e) {
          return require('../../assets/img/emotion/'+e+' (1).png'); 
      }
      ,
    onMonthChange(val) {
      //console.log(val);
      this.todayMonth = val.now.month;
      this.indentifier = val.now.full;
      this.getDiaryByMonth(this.indentifier);
    },
    onNext() {
      this.transitionName = 'slide-left';
    },
    onPrev() {
      this.transitionName = 'slide-right';
    },
    changeDate() {
      this.$refs.calendar.changeDate('2017-12-12');
    },
    deleteItem(title) {
      this.dateData = this.dateData.filter(item => item.title !== title);
    },
    renderHeader({ prev, next, selectedDate }) {
      // console.log(selectedDate)
      const h = this.$createElement;
      const prevButton = h('div', {
        class: ['ui-calendar-modeBtn'],
        on: {
          click: prev
        }
      }, ['prev']);
      const nextButton = h('div', {
        class: ['ui-calendar-modeBtn'],
        on: {
          click: next
        }
      }, ['next']);
      const dateText = h('div', { class: ['ui-calendar-modeBtn'] }, [selectedDate]);
      return h('div', [prevButton, dateText, nextButton]);
    },
   getDiaryByMonth(date){
       // 월별 유저의 다이어리 데이터 가져오는 함수 
           //date = date.slice(6,7);
           //console.log(date)
           //axios 요청 현재월 데이터 요청 
           axios
         .post(constants.baseUrl + "/diary/search", {
           userId : this.$store.state.userId,
           month: this.todayMonth,
           year : 2020
         },{ headers : { "Authorization": "Bearer "+ this.$store.state.authToken} }) // 토큰 인증을 위해 헤더에 내용 추가
         .then(({ data }) => {
            this.dateData = data.diarys
           //console.log(data)
           //alert("수정 완료되었습니다.")
         })
        .catch(function (error) {
           console.log(error);
         });
           //this.dateData = {};
   },
   getDiaryDetail(date){
    //console.log(date+'테스트');
    var diary = {};
    diary = this.dateData.filter(x=> x.date == date);
    //console.log(diary[0])
    this.$emit('showDetail',diary[0]);
   },
   albumUrl(url){
       //10197480
       url = url+'';
       var number  = this.pad(url,8);
       var imgurl = 'https://cdnimg.melon.co.kr/cm/album/images/'+ number.slice(0,3)+'/'+ number.slice(3,5)+'/'+number.slice(5,8)+'/'+url+'_500.jpg';
       return imgurl;
   }
  },
  mounted() {
    //console.log(this.indentifier);
    //this.getDiaryByMonth(this.indentifier);
  }
};

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Rubik&display=swap'); 
*{
    font-family: 'Rubik', sans-serif; 
}
/* .ui-calendar-item-date{
     font-family: 'Rubik', sans-serif; 
} */
.calendar-day-item{
    height:105px;
    width:25px;
    cursor: pointer;
     
}
.albumIcon {
    width: 40px;
    height: 30px;
}
.weatherIcon{
    width:18px;
    height: 18px;
    float:left;
}
.ui-calendar-item-name{
     text-align: center; 
     padding:0;
     margin:0;
}
span{
    font-size: 4px;
}

</style>

