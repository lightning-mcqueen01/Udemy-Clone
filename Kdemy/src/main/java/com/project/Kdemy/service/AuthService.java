package com.project.Kdemy.service;

import com.project.Kdemy.dto.AuthResponseDto;
import com.project.Kdemy.dto.LoginRequestDto;
import com.project.Kdemy.dto.SignupRequest;
import com.project.Kdemy.model.User;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface AuthService {

    void register(SignupRequest req);

    public AuthResponseDto login(LoginRequestDto req);

    public @Nullable List<User> usersInfo();
}
