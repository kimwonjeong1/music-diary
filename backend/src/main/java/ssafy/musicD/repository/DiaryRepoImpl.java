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

import ssafy.musicD.Domain.Diary;
import ssafy.musicD.dto.Song;
import ssafy.musicD.dto.DiaryDto;

@Repository
public class DiaryRepoImpl implements DiaryRepo {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void insertSong(String diaryId, Song song) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(diaryId)));
		Update update = new Update();
		update.set("song", song);
		mongoTemplate.updateFirst(query, update, Diary.class);
	}

	@Override
	public List<Song> findSong(String userId, int month, int year) {
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("userId").is(userId),
				Criteria.where("month").is(month), Criteria.where("year").is(year)));
		List<Diary> tmp = mongoTemplate.find(query, Diary.class);
		List<Song> result = new ArrayList();

		for (Diary diary : tmp) {
			result.add(diary.getSong());
		}

		return result;
	}

	@Override
	public List<DiaryDto> findDiary(String userId, int month, int year) {
		Query query = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("userId").is(userId),
				Criteria.where("month").is(month), Criteria.where("year").is(year)));
		List<Diary> tmp = mongoTemplate.find(query, Diary.class);
		List<DiaryDto> result = new ArrayList();

		for (Diary diary : tmp) {
			DiaryDto tmpStrDIary = new DiaryDto();
			tmpStrDIary.convertId(diary);
			result.add(tmpStrDIary);
		}
		for (DiaryDto strDiary : result) {
			System.out.println("result - " + strDiary);
		}

		return result;
	}

	@Override
	public Boolean insertDiary(Diary diary) {
		mongoTemplate.save(diary);
		return true;
	}

	@Override
	public Boolean deleteDiary(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(new ObjectId(id)));
		mongoTemplate.remove(query, Diary.class);
		return true;
	}

	@Override
	public Boolean updateDiary(Diary diary) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(diary.getId()));
		Update update = new Update();
		update.set("feel", diary.getFeel());
		update.set("weather", diary.getWeather());
		update.set("show", diary.getShow());
		update.set("img", diary.getImg());
		update.set("context", diary.getContext());

		mongoTemplate.updateFirst(query, update, Diary.class);
		return true;
	}
}