package ssafy.musicD.repository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.dto.Song;

public interface SongRepo2 {
    public List<Song> searchSong(String keyword);

}

