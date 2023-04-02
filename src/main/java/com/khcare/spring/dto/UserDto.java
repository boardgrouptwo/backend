package com.khcare.spring.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String user_id;
    private String user_name;
    private String password;
    private String role;
}
