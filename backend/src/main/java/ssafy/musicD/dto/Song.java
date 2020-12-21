package ssafy.musicD.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="emotion")
public class Song {
	private String id;
	private String issue_date;
	private int album_id;
	private String album_name;
	private String song_name;
	private String artist;
	private String lyric;
	private String genre;
	private String youtubeId;
	private double happy;
	private double sad;
	private double angry;
	private double fear;
	private double excited;
	private double indifferent;
}