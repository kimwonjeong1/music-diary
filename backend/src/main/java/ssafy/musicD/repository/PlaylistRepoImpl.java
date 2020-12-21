package ssafy.musicD.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;

@Repository
public class PlaylistRepoImpl implements PlaylistRepo {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Playlist> findAllPlaylist(String userId) {
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(
				Criteria.where("userId").is(userId)
				));
		List<Playlist> result = mongoTemplate.find(query, Playlist.class);
		return result;
	}
	
	@Override
	public Playlist findPlaylist(String playlistId) {
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(
				Criteria.where("id").is(new ObjectId(playlistId))
				));
		Playlist result = mongoTemplate.findOne(query, Playlist.class);
		
		return result;
	}

	@Override
	public void createPlaylist(String userId, String title) {
		Playlist tmp = new Playlist();
		List<Song> songList = new ArrayList<>();
		tmp.setUserId(userId);
		tmp.setSongs(songList);
		tmp.setTitle(title);
		mongoTemplate.save(tmp);
	}

	@Override
	public void deletePlaylist(String playlistId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(playlistId)));
		mongoTemplate.remove(query, Playlist.class);
	}

	@Override
	public void updatePlaylist(String playlistId, String title) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(playlistId)));
		Update update = new Update();
		update.set("title", title);

		mongoTemplate.updateFirst(query, update, Playlist.class);
	}
	
	@Override
	public void insertSong(String playlistId, Song song) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(playlistId)));
		Update update = new Update();
		
		update.push("songs").each(song);
		mongoTemplate.updateFirst(query, update, Playlist.class);
	}
	
	@Override
	public void deleteSong(String playlistId, String songId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(playlistId)));
	    Update update = new Update();
	    
	    update.pull("songs", Query.query(Criteria.where("id").is(new ObjectId(songId))));
	    mongoTemplate.updateFirst(query, update, Playlist.class);
	}

}