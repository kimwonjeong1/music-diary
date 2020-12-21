package ssafy.musicD.repository;

import java.util.List;

import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;

public interface PlaylistRepo {
	public List<Playlist> findAllPlaylist(String userId);
	public Playlist findPlaylist(String playlistId);
	public void createPlaylist(String userId, String title);
	public void updatePlaylist(String playlistId, String title);
	public void deletePlaylist(String playlistId);
	public void insertSong(String playlistId, Song song);
	public void deleteSong(String playlistId, String songId);
}
