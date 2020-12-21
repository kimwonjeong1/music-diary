export default function getCalendarData(d) {
  const date = d ? new Date(d) : new Date();
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();


  const ArrayData = [
    {
      "userId" :"5f7173aafeddb203628a7c5f",
      date: `2020-09-20`,
      "weather":'sun',
      album:'https://cdnimg.melon.co.kr/cm/album/images/101/97/480/10197480_500.jpg'
    },
    {
      "userId" :"5f7173aafeddb203628a7c5f",
      date: `${year}-${month}-${day}`,
      "title": '헤이즈-비도오고그래서',
      "weather":'rain',
      album:'https://cdnimg.melon.co.kr/cm/album/images/100/74/454/10074454_500.jpg'
    },
    {
      "userId" :"5f7173aafeddb203628a7c5f",
      date: `${year}-${month}-${day+1%30}`,
      "title": 'DuaLipa-Dontstart now',
      "weather":'rain',
      album:'https://cdnimg.melon.co.kr/cm2/album/images/103/45/880/10345880_20200326181909_500.jpg',
    },{
      date: `${year}-${month + 1}-15`,
      "title": '"song" festival',
      "weather":'cloudy',
      album:'https://cdnimg.melon.co.kr/cm/album/images/100/74/454/10074454_500.jpg'
    },
    {
      date: `${year}-${month + 2}-6`,
      "title": 'a course of lectures',
      "weather":'cloud',
      album:'https://cdnimg.melon.co.kr/cm/album/images/100/74/454/10074454_500.jpg'
    }
  ];

  [{
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-20",
    "year" : 2020,
    "month" : 9,
    "day" : 20,
    "weather":"sunny",
    "feel":"happy",
    "song" : {
        "songId": "10197480",
        "title": "DNA",
        "singer": "BTS"
    },
    "context":"친구랑 같이 노래방 간날",
    "img":"https://picsum.photos/510/300?1"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-21",
    "year" : 2020,
    "month" : 9,
    "day" : 21,
    "weather":"umbrella",
    "feel":"meh",
    "song" : {
        "songId": "10074454",
        "title": "비도오고그래서",
        "singer": "헤이즈"
    },
    "context":"오늘은 비가 너무 많이 왔다.",
    "img":"https://picsum.photos/510/300?2"
  },{
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-22",
    "year" : 2020,
    "month" : 9,
    "day" : 22,
    "weather":"sunny",
    "feel":"happy",
     "song" : {
        "songId": "10197480",
        "title": "DNA",
        "singer": "BTS"
    },
    "context":"오늘은 날이 너무 좋다. 노래방 가고싶다 ㅎㅎㅎ",
    "img":"https://picsum.photos/510/300?3"
  },
   {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-14",
    "year" : 2020,
    "month" : 9,
    "day" : 14,
    "weather":"cloud",
    "feel":"smile",
    "song" : {
        "songId": "10238683",
        "title": "벌써12시",
        "singer": "청하"
    },
    "context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다",
    "img":"https://picsum.photos/510/300?4"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-11",
    "year" : 2020,
    "month" : 9,
    "day" : 11,
    "weather":"windy",
    "feel":"meh",
    "song" : {
        "songId": "10170069",
        "title": "여행",
        "singer": "볼빨간 사춘기"
    },
    "context":"해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지",
    "img":"https://picsum.photos/510/300?5"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-02",
    "year" : 2020,
    "month" : 9,
    "day" : 2,
    "weather":"storm",
    "feel":"sad",
    "song" : {
        "songId": "10012900",
        "title": "데칼코마니",
        "singer": "마마무"
    },
    "context":"벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ",
    "img":"https://picsum.photos/510/300?5"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-17",
    "year" : 2020,
    "month" : "9",
    "day" : 17,
    "weather":"storm",
    "feel":"smile",
    "song" : {
        "songId": "10096855",
        "title": "가을아침",
        "singer": "아이유"
    },
    "context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-18",
    "year" : 2020,
    "month" : 9,
    "day" : 18,
    "weather":"storm",
    "feel":"smile",
    "song" : {
        "songId": "10001952",
        "title": "숨",
        "singer": "박효신"
    },
    "context":"오늘 구름은 많이 꼈지만 친구만나서 기분 좋아짐 ;)",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-08",
    "year" : 2020,
    "month" : 9,
    "day" : 8,
    "weather":"umbrella",
    "feel":"smile",
    "song" : {
        "songId": "10001952",
        "title": "숨",
        "singer": "박효신"
    },
    "context":"오늘 아무것도 안해서 심심했다.",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-09-09",
    "year" : 2020,
    "month" : 9,
    "day" : 9,
    "weather":"umbrella",
    "feel":"smile",
    "song" : {
        "songId": "10217134",
        "title": "너를 만나",
        "singer": "폴킴"
    },
    "context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-21",
    "year" : 2020,
    "month" : 9,
    "day" : 21,
    "weather":"sunny",
    "feel":"happy",
    "song" : {
        "songId": "10197480",
        "title": "DNA",
        "singer": "BTS"
    },
    "context":"친구랑 같이 노래방 간날",
    "img":"https://picsum.photos/510/300?1"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-22",
    "year" : 2020,
    "month" : 9,
    "day" : 20,
    "weather":"umbrella",
    "feel":"meh",
    "song" : {
        "songId": "10074454",
        "title": "비도오고그래서",
        "singer": "헤이즈"
    },
    "context":"오늘은 비가 너무 많이 왔다.",
    "img":"https://picsum.photos/510/300?2"
  },{
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-01",
    "year" : 2020,
    "month" : 9,
    "day" : "20",
    "weather":"sunny",
    "feel":"happy",
     "song" : {
        "songId": "10197480",
        "title": "DNA",
        "singer": "BTS"
    },
    "context":"오늘은 날이 너무 좋다. 노래방 가고싶다 ㅎㅎㅎ",
    "img":"https://picsum.photos/510/300?3"
  },
   {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-15",
    "year" : "2020",
    "month" : 9,
    "day" : 20,
    "weather":"cloud",
    "feel":"smile",
    "song" : {
        "songId": "10238683",
        "title": "벌써12시",
        "singer": "청하"
    },
    "context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다",
    "img":"https://picsum.photos/510/300?4"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-12",
    "year" : 2020,
    "month" : 9,
    "day" : 20,
    "weather":"windy",
    "feel":"meh",
    "song" : {
        "songId": "10170069",
        "title": "여행",
        "singer": "볼빨간 사춘기"
    },
    "context":"해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지",
    "img":"https://picsum.photos/510/300?5"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-02",
    "weather":"storm",
    "feel":"sad",
    "song" : {
        "songId": "10012900",
        "title": "데칼코마니",
        "singer": "마마무"
    },
    "context":"벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ",
    "img":"https://picsum.photos/510/300?5"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-17",
    "weather":"storm",
    "feel":"smile",
    "song" : {
        "songId": "10096855",
        "title": "가을아침",
        "singer": "아이유"
    },
    "context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-30",
    "weather":"storm",
    "feel":"smile",
    "song" : {
        "songId": "10001952",
        "title": "숨",
        "singer": "박효신"
    },
    "context":"오늘 구름은 많이 꼈지만 친구만나서 기분 좋아짐 ;)",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-05",
    "weather":"umbrella",
    "feel":"smile",
    "song" : {
        "songId": "10001952",
        "title": "숨",
        "singer": "박효신"
    },
    "context":"오늘 아무것도 안해서 심심했다.",
    "img":"https://picsum.photos/510/300?6"
  },
  {
    "userId" :"5f7173aafeddb203628a7c5f",
    "date": "2020-08-09",
    "weather":"windy",
    "feel":"smile",
    "song" : {
        "songId": "10217134",
        "title": "너를 만나",
        "singer": "폴킴"
    },
    "context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다",
    "img":"https://picsum.photos/510/300?6"
  },
  ]

  [{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-14","year" : 2020,"month" : 9, "day" : 14,"weather":"cloud","feel":"smile","song" : {"songId": "10238683","title": "벌써12시","singer": "청하"},"context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다","img":"https://picsum.photos/510/300?4"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-11","year" : 2020,"month" : 9,"day" : 11,"weather":"windy","feel":"meh","song" : {"songId": "10170069","title": "여행","singer": "볼빨간 사춘기"},"context":"해외든 국내든 여행가고싶다ㅜㅜ 노래로 대신 여행기분 느껴야지","img":"https://picsum.photos/510/300?5"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-02","year" : 2020,"month" : 9,"day" : 2,"weather":"storm","feel":"sad","song" : {"songId": "10012900","title": "데칼코마니","singer": "마마무"},"context":"벌써 가을이다. 저녁되면 쌀쌀해지는듯 ㅜㅜ","img":"https://picsum.photos/510/300?5"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-17","year" : 2020,"month" : "9","day" : 17,"weather":"storm","feel":"smile","song" : {"songId": "10096855","title": "가을아침","singer": "아이유"},"context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다","img":"https://picsum.photos/510/300?6"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-18","year" : 2020,"month" : 9,"day" : 18,"weather":"storm","feel":"smile","song" : {"songId": "10001952","title": "숨","singer": "박효신"},"context":"오늘 구름은 많이 꼈지만 친구만나서 기분좋아짐;)","img":"https://picsum.photos/510/300?6"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-08","year" : 2020,"month" : 9,"day" : 8,"weather":"umbrella","feel":"smile","song" : {"songId": "10001952","title": "숨","singer": "박효신"},"context":"오늘 아무것도 안해서 심심했다.","img":"https://picsum.photos/510/300?6"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-09-09","year" : 2020,"month" : 9,"day" : 9,"weather":"umbrella","feel":"smile","song" : {"songId": "10217134","title": "너를 만나","singer": "폴킴"},"context":"오늘 친구들이랑 밤늦게 수다 떠느라 시간이 금방 지나갔다. 다들 바빠서 자주 못만나는게 좀 아쉽다","img":"https://picsum.photos/510/300?6"}
,{"userId" :"5f7173aafeddb203628a7c5f","date": "2020-08-21","year" : 2020,"month" : 9,"day" : 21,"weather":"sunny","feel":"happy","song" : {"songId": "10197480","title": "DNA","singer": "BTS"},"context":"친구랑 같이 노래방 간날","img":"https://picsum.photos/510/300?1"}]
  /* {
      "date": "${year}-${month}-${day}`,
      "title": "shopping"
    }, */

/*   const ObjectData = {};

  ArrayData.forEach((item) => {
    ObjectData[item."date"] = { ...item };
  }); */

  return {
    Array: ArrayData,
    /* Object: ObjectData */
  };
}

// export default {
//   Array: ArrayData,
//   Object: ObjectData
// };