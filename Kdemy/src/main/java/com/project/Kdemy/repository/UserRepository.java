package com.project.Kdemy.repository;


import com.project.Kdemy.dto.UserResponseDto;
import com.project.Kdemy.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<UserResponseDto> findByRole(String role);
}