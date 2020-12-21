package ssafy.musicD.dto;

import lombok.Data;
import ssafy.musicD.Domain.Diary;

@Data
public class DiaryDto {
	private String id;
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

	public void convertId(Diary Diary) {
		if(Diary.getId() != null)
			id = Diary.getId().toString();
		userId = Diary.getUserId();
		year = Diary.getYear();
		month = Diary.getMonth();
		day = Diary.getDay();
		date = Diary.getDate();
		feel = Diary.getFeel();
		weather = Diary.getWeather();
		song = Diary.getSong();
		show = Diary.getShow();
		img = Diary.getImg();
		context = Diary.getContext();
	}
}
