package ssafy.musicD.Domain;

import java.util.List;

//import java.util.HashSet;
//import java.util.Set;

import javax.validation.constraints.Email;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user")
public class Member {
	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@Email
	private String email;
	private String password;
	@Indexed(unique = true)
	private String nickname;
//	private String name;
//	private Date regdate;
	private String profileURL;
	private List<String> friends;
	private List<String> waitFriends;
	private String role;
	
//	@DBRef
//	private Set<Role> roles = new HashSet<>();

}
