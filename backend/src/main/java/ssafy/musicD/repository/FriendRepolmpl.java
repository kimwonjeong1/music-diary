package ssafy.musicD.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ssafy.musicD.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendRepolmpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    private boolean False;

    public List<MemberDto> searchFriends(String keyword, String userId) {
        Query query1 = new Query(Criteria.where("_id").is(userId));
        MemberDto userSelf = mongoTemplate.findOne(query1, MemberDto.class, "user");
        List<String> userSelfFriend = userSelf.getFriends();                                  //1 요청한사람의 친구 리스트
        List<MemberDto> searchFriendOfKeyword = new ArrayList<>();
        for (int i = 0; i < userSelfFriend.size(); i++) {
            System.out.println(userSelfFriend.get(i));
            Query query2 = new Query(Criteria.where("_id").is(userSelfFriend.get(i)));
            MemberDto temp = mongoTemplate.findOne(query2, MemberDto.class, "user");
            System.out.println(temp);
            System.out.println(keyword);
            if (temp.getNickname().contains(keyword)) {
                searchFriendOfKeyword.add(temp);
            }

        }
        return searchFriendOfKeyword;
    }


    public List<MemberDto> waitFriendList(String userId){
        Query query = new Query(Criteria.where("_id").is(userId));
        MemberDto temp = mongoTemplate.findOne(query, MemberDto.class,"user");
        List<String> waitUserFriendList = temp.getWaitFriends();
        List<MemberDto> waitFriendList = new ArrayList<>();
        for (int i = 0; i<waitUserFriendList.size(); i++){
            String friendId = waitUserFriendList.get(i);
            Query query1 = new Query(Criteria.where("_id").is(friendId));
            MemberDto friend = mongoTemplate.findOne(query1, MemberDto.class,"user");
            waitFriendList.add(friend);
        }
        return waitFriendList;
    }



    public List<MemberDto> searchNonFriend(String keyword, String userId){
        Query query = new Query(Criteria.where("Nickname").regex(keyword));
        List<MemberDto> keywordFriend = mongoTemplate.find(query, MemberDto.class, "user");
        System.out.println(keywordFriend);
        Query query2 = new Query(Criteria.where("_id").is(userId));
        MemberDto userInfo = mongoTemplate.findOne(query2, MemberDto.class,"user");
        List<String> allUserOfKeyword = new ArrayList<>();
        List<MemberDto> nonFriendList = new ArrayList<>();
        for (MemberDto user : keywordFriend) {
            allUserOfKeyword.add(user.getId());
        }
        System.out.println(allUserOfKeyword);
        List<String> userInfoFriend = userInfo.getFriends();
            for (int i = 0; i < allUserOfKeyword.size(); i++) {
                String checkUserId = allUserOfKeyword.get(i);
                if (userInfoFriend.contains(checkUserId)) {
                    continue;
                }else{
                    Query query3 = new Query(Criteria.where("_id").is(checkUserId));
                    MemberDto temp = mongoTemplate.findOne(query3, MemberDto.class,"user");
                    nonFriendList.add(temp);
                }
                }
        return nonFriendList;
    }

    public List<MemberDto> friendList(String userId){
        Query query = new Query(Criteria.where("_id").is(userId));
        MemberDto temp = mongoTemplate.findOne(query, MemberDto.class,"user");
        List<String> userFriendList = temp.getFriends();
        List<MemberDto> friendList = new ArrayList<>();
        for (int i = 0; i<userFriendList.size(); i++){
            String friendId = userFriendList.get(i);
            Query query1 = new Query(Criteria.where("_id").is(friendId));
            MemberDto friend = mongoTemplate.findOne(query1, MemberDto.class,"user");
            friendList.add(friend);
        }
        return friendList;
    }

    public void requestFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(friendId));
        MemberDto receiveUser = mongoTemplate.findOne(query, MemberDto.class,"user");
        List<String> waitFriends = receiveUser.getWaitFriends();
        if (waitFriends.contains(userId)==False){
            waitFriends.add(userId);
            Update update = new Update();
            update.set("waitFriends", waitFriends);
            mongoTemplate.updateFirst(query, update, "user");
        }
    }

    public void acceptFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(userId));
        Query query1 = new Query(Criteria.where("_id").is(friendId));
        MemberDto sendUser = mongoTemplate.findOne(query1, MemberDto.class,"user");
        MemberDto receiveUser = mongoTemplate.findOne(query, MemberDto.class, "user");
        List<String> receiveListed = receiveUser.getFriends();
        List<String> sendListed = sendUser.getFriends();
        receiveListed.add(friendId);
        sendListed.add(userId);
        Update update = new Update();
        update.set("friends",receiveListed);
        mongoTemplate.updateFirst(query,update,"user");
        Update update1 = new Update();
        update1.set("friends",sendListed);
        mongoTemplate.updateFirst(query1,update1,"user");

        List<String> waitSendListed = sendUser.getWaitFriends();
        List<String> waitReceiveListed = receiveUser.getWaitFriends();
        waitSendListed.remove(userId);
        waitReceiveListed.remove(friendId);

        Update update2 = new Update();
        update2.set("waitFriends",waitSendListed);
        mongoTemplate.updateFirst(query1,update2,"user");
        Update update3 = new Update();
        update3.set("waitFriends",waitReceiveListed);
        mongoTemplate.updateFirst(query,update3,"user");
    }

    public void deleteFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(userId));
        Query query1 = new Query(Criteria.where("_id").is(friendId));
        MemberDto receiveUser = mongoTemplate.findOne(query, MemberDto.class, "user");
        MemberDto sendUser = mongoTemplate.findOne(query1, MemberDto.class,"user");
        List<String> receivelisted = receiveUser.getFriends();
        List<String> sendlisted = sendUser.getFriends();
        receivelisted.remove(friendId);
        sendlisted.remove(userId);
        Update update = new Update();
        update.set("friends",receivelisted);
        mongoTemplate.updateFirst(query,update,"user");
        Update update1 = new Update();
        update1.set("friends",sendlisted);
        mongoTemplate.updateFirst(query1,update1,"user");
//        System.out.println("str");

    }

    public void refuseFriend(String userId,String friendId){
        Query query = new Query(Criteria.where("_id").is(userId));
        MemberDto user = mongoTemplate.findOne(query, MemberDto.class,"user");
        List<String> userWaitFriend = user.getWaitFriends();
        userWaitFriend.remove(friendId);
        Update update = new Update();
        update.set("waitFriends",userWaitFriend);
        mongoTemplate.updateFirst(query,update,"user");
    }


}
