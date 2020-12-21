package ssafy.musicD.Domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import ssafy.musicD.dto.Song;
import ssafy.musicD.dto.DiaryDto;

@NoArgsConstructor
@Data
@Document(collection="diary")
public class Diary {
	@Id
	private ObjectId id;
	private String userId;
	private int year;
	private int month;
	private int day;
	private String date;
	private String feel;
	private String weather;
	private Song song;
	private Boolean show;
	private String img;
	private String context;
	
	public void convertId(DiaryDto strDiary) {
		if(strDiary.getId() != null)
			id = new ObjectId(strDiary.getId());
		userId = strDiary.getUserId();
		year = strDiary.getYear();
		month = strDiary.getMonth();
		day = strDiary.getDay();
		date = strDiary.getDate();
		feel = strDiary.getFeel();
		weather = strDiary.getWeather();
		song = strDiary.getSong();
		show = strDiary.getShow();
		img = strDiary.getImg();
		context = strDiary.getContext();
	}
}
