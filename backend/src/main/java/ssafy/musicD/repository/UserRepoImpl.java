package ssafy.musicD.repository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ssafy.musicD.dto.MemberDto;

@Repository
public class UserRepoImpl implements UserRepo2 {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void updateUserInfo(MemberDto user) {
		Criteria criteria = new Criteria("_id");
		criteria.is(new ObjectId(user.getId()));
		Query query = new Query(criteria);

		Update update = new Update();
		if(user.getNickname() != null)
			update.set("nickname", user.getNickname());
		if(user.getProfileURL() != null)
			update.set("profileURL", user.getProfileURL());

		mongoTemplate.updateFirst(query, update, "user");
	}

//	public void deleteUser(String userId) {
//		Criteria criteria = new Criteria("_id");
//		criteria.is(userId);
//		Query query = new Query(criteria);
//
//		mongoTemplate.remove(query, "user");
//	}

}
