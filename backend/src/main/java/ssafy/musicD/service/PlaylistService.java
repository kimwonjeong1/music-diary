package ssafy.musicD.service;

import java.util.List;

import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;

public interface PlaylistService {
	public List<Song> getMonthPlaylist(String userId, int month, int year); 
	public Playlist getDetailPlaylist(String playlistId);
	public List<Playlist> getPlaylist(String userId);
	public void createPlaylist(String userId, String title);
	public void updatePlaylist(String playlistId, String title);
	public void deletePlaylist(String playlistId);
	public void insertSong(String playlistId, String songId);
	public void deleteSong(String playlistId, String songId);
}
	