package com.example.board.api.dtos.userDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFindDto {
    private Long id;
    private String nickName;
    private String name;
}
