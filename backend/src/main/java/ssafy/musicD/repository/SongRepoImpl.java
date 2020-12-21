package ssafy.musicD.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ssafy.musicD.dto.Song;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepoImpl implements SongRepo2 {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Song> searchSong(String keyword) {
		Query query = new Query(Criteria.where("song_name").regex(keyword));
		System.out.println(keyword);
		List<Song> keywordSong = mongoTemplate.find(query, Song.class, "emotion");
		return keywordSong;
	}

}
