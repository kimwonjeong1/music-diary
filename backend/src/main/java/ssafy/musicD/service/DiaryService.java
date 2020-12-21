package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.Domain.Diary;
import ssafy.musicD.dto.DiaryDto;

public interface DiaryService {
	public List<DiaryDto> findDiaryByMonth(String userId, int month, int year);
	public boolean insertDiary(Diary diary);
	public boolean updateDiary(Diary diary);
	public boolean deleteDiary(String id);
	public void insertSong(String diaryId, String songId);
}
