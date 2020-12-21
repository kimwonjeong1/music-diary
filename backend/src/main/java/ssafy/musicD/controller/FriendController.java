package ssafy.musicD.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.musicD.dto.MemberDto;
import ssafy.musicD.service.FriendServicelmpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "*")
public class FriendController {
    @Autowired
    private FriendServicelmpl friendService;

    @ApiOperation(value = "친구 검색", response = String.class)
    @GetMapping("/friend")
    public List<MemberDto> searchFriends(@RequestParam String keyword,@RequestParam String userId) {
        List<MemberDto> searchFriendList = friendService.searchFriends(keyword, userId);
        return searchFriendList;
    }

    @ApiOperation(value = "친구 아닌 유저 검색", response = String.class)
    @GetMapping("/nonfriend")
    public List<MemberDto> searchNonFriends(@RequestParam String keyword, @RequestParam String userId) {
        List<MemberDto> searchNonFriendList = friendService.searchNonFriend(keyword, userId);
        return searchNonFriendList;
    }

    @ApiOperation(value = "친구 목록 조회", response = String.class)
    @GetMapping("/friend/{userId}")
    public List<MemberDto> friendList(@PathVariable String userId) {
        List<MemberDto> friendList = friendService.friendList(userId);
        return friendList;
    }

    @ApiOperation(value = "친구 요청 보내기", response = String.class)
    @PostMapping("/friend/request")
    public ResponseEntity<Map<String, Object>> requestFriend(@RequestBody Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String userId = (String) map.get("userId");
        String friendId = (String) map.get("friendId");
        try {
            friendService.requestFriend(userId, friendId);
            status = HttpStatus.OK;
            resultMap.put("message", "친구 신청이 완료되었습니다.");

        } catch (RuntimeException e) {
            status = HttpStatus.BAD_REQUEST;
            resultMap.put("status", status.value());
            resultMap.put("message", e.getMessage());
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "친구 요청 수락", response = String.class)
    @PutMapping("/friend/accept")
    public ResponseEntity<Map<String, Object>> acceptFriend(@RequestBody Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String userId = (String) map.get("userId");
        String friendId = (String) map.get("friendId");
        try {
            friendService.acceptFriend(userId, friendId);
            status = HttpStatus.OK;
            resultMap.put("message", "친구 요청에 수락하셨습니다.");
        } catch (RuntimeException e) {
            status = HttpStatus.BAD_REQUEST;
            resultMap.put("status", status.value());
            resultMap.put("message", e.getMessage());
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "나에게 친구요청한 유저 목록 조회", response = String.class)
    @GetMapping("/friend/request/list/{userId}")
    public List<MemberDto> waitFriendList(@PathVariable String userId) {
        List<MemberDto> waitFriendList = friendService.waitFriendList(userId);
        return waitFriendList;
    }

    @ApiOperation(value = "친구 삭제", response = String.class)
    @DeleteMapping("/friend")
    public ResponseEntity<Map<String, Object>> deleteFriend(@RequestParam String friendId, @RequestParam String userId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;;
        try {
            friendService.deleteFriend(userId, friendId);
            status = HttpStatus.OK;
            resultMap.put("message", "친구를 삭제하셨습니다..");
        } catch (RuntimeException e) {
            status = HttpStatus.BAD_REQUEST;
            resultMap.put("status", status.value());
            resultMap.put("message", e.getMessage());
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "친구 요청 거절 ", response = String.class)
    @PutMapping("/friend/noaccept")
    public ResponseEntity<Map<String, Object>> refuseFriend(@RequestBody Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        String userId = (String) map.get("userId");
        String friendId = (String) map.get("friendId");
        try {
            friendService.refuseFriend(userId, friendId);
            status = HttpStatus.OK;
            resultMap.put("message", "친구 요청을 거절하셨습니다.");
        } catch (RuntimeException e) {
            status = HttpStatus.BAD_REQUEST;
            resultMap.put("status", status.value());
            resultMap.put("message", e.getMessage());
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
