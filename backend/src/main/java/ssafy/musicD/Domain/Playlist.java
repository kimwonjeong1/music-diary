package ssafy.musicD.Domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import ssafy.musicD.dto.Song;

@Data
@Document(collection="playlist")
public class Playlist {
	private String id;
	private String userId;
	private String title;
	private List<Song> songs;
}