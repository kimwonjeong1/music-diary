# Mongo DB 기초

환경설정 bin 파일을 환경 변수에다 추가해서 cmd에 version 확인가능





##### mongod --dbpath (경로) --port 27017 (기본 포트번호이다)



##### show dbs  

![image-20200909123239794](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200909123239794.png)



use (이름) 후에 db 검색하면 

![image-20200909123313462](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200909123313462.png)



db.createCollection("user") 하고 show collections 하면 객체 확인가능

![image-20200909123433461](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200909123433461.png)

![image-20200909123546636](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200909123546636.png)

db.(이름).find() 하면 객체확인할수 있으며 뒤에 .pretty() 추가해주면 JSON형태로 볼수있다.







# port 

실행중인 포트 찾기 

netstat -a -o



포트 죽이기

taskkill /f /pid (pid 번호)







# 쿼리문

### insert

db.테이블.insert({ 필드 : 값},{필드:값},{필드:값})

- ex)![image-20200910104730796](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910104730796.png)

### count

#### db.테이블.count()

#### db.테이블.find().count()

![image-20200910112431732](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112431732.png)



#### show table

![image-20200910112447851](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112447851.png)

#### db.테이블.find()

![image-20200910112458651](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112458651.png)



##### db.테이블.find({필드:값}) 하면 객체 전체를 확인할수 있다

![image-20200910112557140](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112557140.png)

##### db.테이블.find({필드:값}).pretty(): JSON형태로 데이터를 확인가능하다.

![image-20200910124648502](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910124648502.png)

##### db.테이블.find({필드 : {%(gt or gte or lt or lte): 값}, 필드:1 , 필드:1 , 필드:0 }) : 조건에 맞는 객체중에 필드가 1로 표시된것들만 빼네옴 

![image-20200910112808396](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112808396.png)

![image-20200910112904001](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112904001.png)

##### db.테이블.find({필드 : {%(gt or gte or lt or lte): 값}, 필드:1 , 필드:1 , 필드:0 }.sort({필드:-1}):내림차순 아무것도 안쓰면 오름차순이다

![image-20200910112953415](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910112953415.png)

![image-20200910124837659](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910124837659.png)

![image-20200910124858416](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910124858416.png)





### UPDATE

업데이트문은 4가지의 인자값이 필요하다,

1번:조건

2번:변경값

3번:조건이 해당하는 값이 없고 이 값이 TRUE 면 해당값으로 INSERT, 이값이 FALSE 면 무시( 기본값 FALSE)

4번 : 해당테이블 전체에 적용 TRUE/FALSE(기본값FALSE)





##### db.테이블.update({필드 : 값},{$set:{필드:값}})      처음 필드는 조건 두번째필드는 수정값

![image-20200910115045083](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910115045083.png)

db.테이블.update({필드 : 값},{$set:{필드:값}} , True) : 조건에 맞는 값이 없으면 해당값을 insert 하라는 명령어이다

![image-20200910115738679](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910115738679.png)

### remove

db.테이블명.remove({필드:값}) : 필드값에 만족하는 객체가 삭제된다.

![image-20200910120400150](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910120400150.png)

db.테이블명.remove({필드:{$gt:숫자}}): 조건에 맞는걸 전부다 삭제

 

![image-20200910120459475](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910120459475.png)



그외에도 db.테이블.remove() : 테이블 데이터 전체 삭제 , db.테이블.drop(): 테이블 삭제

![image-20200910120550527](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910120550527.png)

![image-20200910120556962](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910120556962.png)





### 연산자 정리

![image-20200910123057362](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910123057362.png)

### 논리 연산자

![image-20200910124150384](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910124150384.png)



논리 연산자 예시:

db.테이블명,find({$or : [{"필드명":"값"},{"필드명":"값"}]})

ex)

![image-20200910125033594](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910125033594.png)

![image-20200910130016172](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910130016172.png)





### $regex 연산자

![image-20200910130403386](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910130403386.png)





### $where 연산자

![image-20200910130844179](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910130844179.png)

### $elemMatch 연산자

![image-20200910130929398](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910130929398.png)





### $slice 연산자

limit 역할을 한다.

![image-20200910132457179](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910132457179.png)

1개만 출력

![image-20200910133044970](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200910133044970.png)