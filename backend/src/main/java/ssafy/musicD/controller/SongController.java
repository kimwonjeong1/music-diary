package ssafy.musicD.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ssafy.musicD.dto.Song;
import ssafy.musicD.service.SongService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/music")
    @ApiOperation(value = "노래 검색", response = String.class)
    public List<Song> searchSong(@RequestParam String keyword) {
        List<Song> keywordSongList = songService.searchSong(keyword);
        return keywordSongList;
    }
}
