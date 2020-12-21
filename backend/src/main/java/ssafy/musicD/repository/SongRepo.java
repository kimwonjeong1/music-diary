package ssafy.musicD.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.dto.Song;

public interface SongRepo extends MongoRepository<Song, String> {

}

