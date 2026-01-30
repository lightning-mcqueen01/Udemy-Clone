package com.project.Kdemy.controller;

import com.project.Kdemy.dto.AuthResponseDto;
import com.project.Kdemy.dto.LoginRequestDto;
import com.project.Kdemy.dto.SignupRequest;
import com.project.Kdemy.model.User;
import com.project.Kdemy.service.ServiceImpl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Data
@AllArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User created");

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getusers() {
        return ResponseEntity.ok(authService.usersInfo());
    }
}

