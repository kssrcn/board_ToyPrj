package com.example.board.api;

import com.example.board.api.dtos.userDto.UserFindDto;
import com.example.board.api.dtos.userDto.UserJoinDto;
import com.example.board.domain.User;
import com.example.board.service.userService.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    //전체 조회
    @GetMapping("/users")
    public Result<List<UserFindDto>> findAll() {
        List<User> users = userService.findAll();
        return new Result<>(users.stream()
                .map(user -> new UserFindDto(user.getId(), user.getNickName(), user.getName()))
                .collect(Collectors.toList()));
    }

    //회원 가입
    @PostMapping("/user")
    public UserJoinResponseDto join(@RequestBody UserJoinDto userJoinDto) {
        User user = User.createUser(userJoinDto.getNickName(), userJoinDto.getName());
        Long joinId = userService.join(user);
        return new UserJoinResponseDto(joinId);
    }


    //회원 삭제
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.delete(userId);
    }

    @Data
    static class UserJoinResponseDto {
        private Long id;

        public UserJoinResponseDto() {
        }

        public UserJoinResponseDto(Long id) {
            this.id = id;
        }
    }



}
