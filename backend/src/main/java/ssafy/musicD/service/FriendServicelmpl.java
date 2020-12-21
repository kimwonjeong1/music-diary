package ssafy.musicD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssafy.musicD.dto.MemberDto;
import ssafy.musicD.repository.FriendRepolmpl;

import java.util.List;

@Service
public class FriendServicelmpl implements FriendService {
    @Autowired
    private FriendRepolmpl friendRepo;

    public List<MemberDto> searchFriends(String keyword , String userId){
        return friendRepo.searchFriends(keyword,userId);
    }

    public List<MemberDto> waitFriendList(String userId) { return friendRepo.waitFriendList(userId); }

    public List<MemberDto> searchNonFriend(String keyword, String userId) { return friendRepo.searchNonFriend(keyword,userId);}

    public List<MemberDto> friendList(String userId) { return friendRepo.friendList(userId);}

    public void requestFriend(String userId, String friendId) {friendRepo.requestFriend(userId,friendId);}

    public void acceptFriend(String userId,String friendId) {friendRepo.acceptFriend(userId,friendId);}

    public void deleteFriend(String userId,String friendId) {friendRepo.deleteFriend(userId,friendId);}

    public void refuseFriend(String userId,String friendId) {friendRepo.refuseFriend(userId,friendId);}
}
