package ssafy.musicD.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ssafy.musicD.Domain.Diary;
import ssafy.musicD.Domain.Playlist;
import ssafy.musicD.dto.Song;
import ssafy.musicD.dto.DiaryDto;
import ssafy.musicD.service.DiaryService;
import ssafy.musicD.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;
	
	// 월별 플레이리스트 조회
	@PostMapping("/month")
	@ApiOperation(value = "월별 플레이리스트 조회", response = String.class)
	public Map<String, Object> getMonthPlaylist(@RequestBody Map<String, Object> m) {
		String userId = (String)m.get("userId");
		int month = (int)m.get("month");
		int year = (int)m.get("year");
		List<Song> playlist = playlistService.getMonthPlaylist(userId, month, year);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("playlist", playlist);
		return map;
	}
	
	
	// 플레이리스트 목록 조회
	@GetMapping("/{userId}")
	@ApiOperation(value = "나만의 플레이리스트 목록 조회", response = String.class)
	public Map<String, Object> getPlaylist(@PathVariable String userId) {
		List<Playlist> playlists = playlistService.getPlaylist(userId);

		Map<String, Object> map = new HashMap<>();
		
		map.put("status", 200);
		map.put("playlists", playlists);
		
		return map;
	}
	
	// 플레이리스트 상세 조회
	@GetMapping("/detail/{playlistId}")
	@ApiOperation(value = "플레이리스트 상세 조회", response = String.class)
	public Map<String, Object> getDetailPlaylist(@PathVariable String playlistId) {
		Playlist playlist = playlistService.getDetailPlaylist(playlistId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("playlist", playlist);
		return map;
	}
	
	
	// 플레이리스트 생성
	@PostMapping
	@ApiOperation(value = "플레이리스트 생성", response = String.class)
	public Map<String, Object> createPlaylist(@RequestBody Map<String, String> m) {
		String userId = m.get("userId");
		String title = m.get("title");
		playlistService.createPlaylist(userId, title);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 수정
	@PutMapping
	@ApiOperation(value = "플레이리스트 수정", response = String.class)
	public Map<String, Object> updatePlaylist(@RequestBody Playlist playlist) {
		String playlistId = playlist.getId();
		String title = playlist.getTitle();
		playlistService.updatePlaylist(playlistId, title);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 삭제
	@DeleteMapping("/{playlistId}")
	@ApiOperation(value = "플레이리스트 삭제", response = String.class)
	public Map<String, Object> deletePlaylist(@PathVariable String playlistId) {
		playlistService.deletePlaylist(playlistId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 노래 추가
	@PostMapping("/song")
	@ApiOperation(value = "플레이리스트 노래 추가", response = String.class)
	public Map<String, Object> addPlaylistSong(@RequestBody Map<String, String> m) {
		String playlistId = m.get("playlistId");
		String songId = m.get("songId");
		playlistService.insertSong(playlistId, songId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	// 플레이리스트 노래 삭제
	@PostMapping("/deletesong")
	@ApiOperation(value = "플레이리스트 노래 삭제", response = String.class)
	public Map<String, Object> deletePlaylistSong(@RequestBody Map<String, String> m) {
		String playlistId = m.get("playlistId");
		String songId = m.get("songId");
		playlistService.deleteSong(playlistId, songId);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}

	
}
