package ssafy.musicD.dto;

import java.util.List;

import lombok.Data;
import ssafy.musicD.Domain.Member;

@Data
public class MemberDto {
    private String id;
    private String email;
    private String nickname;
    private String profileURL;
    private List<String> friends;
    private List<String> waitFriends;
    private String role;
    
	public void convertId(Member m) {
		id = m.getId().toString();
		email = m.getEmail();
		nickname = m.getNickname();
		profileURL = m.getProfileURL();
		friends = m.getFriends();
		waitFriends = m.getWaitFriends();
		role = m.getRole();
	}
}