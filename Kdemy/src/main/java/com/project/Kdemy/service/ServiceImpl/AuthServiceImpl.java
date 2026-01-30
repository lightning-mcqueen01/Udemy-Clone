package com.project.Kdemy.service.ServiceImpl;

import com.project.Kdemy.dto.AuthResponseDto;
import com.project.Kdemy.dto.LoginRequestDto;
import com.project.Kdemy.dto.SignupRequest;
import com.project.Kdemy.model.User;
import com.project.Kdemy.repository.UserRepository;
import com.project.Kdemy.security.JwtUtil;
import com.project.Kdemy.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void register(SignupRequest req) {

        if(userRepository.existsByEmail(req.getEmail())){
            throw new RuntimeException("User Already Exist");
        }

        System.out.println("hihihi");

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());
        user.setRole(req.getRole());
        userRepository.save(user);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto req) {

        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(() ->
                    new RuntimeException("invalid Credentials errrrror"));

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw  new RuntimeException("invalid credentials trrrrrr");
        }

        String token = jwtUtil.generateToken(req.getEmail());

        return new AuthResponseDto(token, user.getRole().name());

        
    }

    @Override
    public @Nullable List<User> usersInfo() {
        return userRepository.findAll();
    }
}
