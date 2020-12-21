package ssafy.musicD.repository;

//	public void signUp(User user);
//	public void updateUserInfo(User user);
//	public void deleteUser(String userId);
//	public String checkEmail(String email);
//	public String checkNickname(String nickname);
import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.Domain.Member;

public interface UserRepo extends MongoRepository<Member, String> {
	Member findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByNickname(String nickname);
	
	Long deleteById(Long id);
	
}
