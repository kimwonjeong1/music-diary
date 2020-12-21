package ssafy.musicD.service;

import ssafy.musicD.dto.Song;

import java.util.List;

public interface SongService {

    public List<Song> searchSong(String keyword);
}
