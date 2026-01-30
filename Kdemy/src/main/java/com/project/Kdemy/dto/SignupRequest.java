package com.project.Kdemy.dto;

import com.project.Kdemy.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String email;
    private String username;
    private String password;
    private String phone;
    private Role role;

}
