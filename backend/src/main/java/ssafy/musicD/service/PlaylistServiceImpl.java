package ssafy.musicD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;
import ssafy.musicD.repository.DiaryRepo;
import ssafy.musicD.repository.PlaylistRepo;
import ssafy.musicD.repository.SongRepo;
import ssafy.musicD.youtube.Youtube;

@Service
public class PlaylistServiceImpl implements PlaylistService {
	@Autowired
	private PlaylistRepo playlistRepo;
	@Autowired
	private SongRepo songRepo;
	@Autowired
	private DiaryRepo diaryRepo;
	@Autowired
	private Youtube youtube;

	@Override
	public List<Song> getMonthPlaylist(String userId, int month, int year) {
		return diaryRepo.findSong(userId, month, year);
	}

	@Override
	public Playlist getDetailPlaylist(String playlistId) {
		return playlistRepo.findPlaylist(playlistId);
	}

	@Override
	public List<Playlist> getPlaylist(String userId) {
		return playlistRepo.findAllPlaylist(userId);
	}

	@Override
	public void createPlaylist(String userId, String title) {
		playlistRepo.createPlaylist(userId, title);
	}

	@Override
	public void updatePlaylist(String playlistId, String title) {
		playlistRepo.updatePlaylist(playlistId, title);
	}

	@Override
	public void deletePlaylist(String playlistId) {
		playlistRepo.deletePlaylist(playlistId);
	}

	@Override
	public void insertSong(String playlistId, String songId) {
		Song song = songRepo.findById(songId).orElse(null);
		String search = song.getArtist()+" "+song.getSong_name();
		System.out.println(search);
		String videoId = youtube.getVideoId(search);
		System.out.println(videoId);
		song.setYoutubeId(videoId);
		System.out.println(song);
		playlistRepo.insertSong(playlistId, song);
	}

	@Override
	public void deleteSong(String playlistId, String songId) {
		playlistRepo.deleteSong(playlistId, songId);
	}
}
