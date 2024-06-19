package com.example.javaspringbootb17.repsitory;

import com.example.javaspringbootb17.entity.User;
import com.example.javaspringbootb17.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    Page<User> findByRole(Role role, Pageable pageable);
    List<User> findByNameContainingIgnoreCase(String keyword);
    List<User> findByAvatarIsNotNull();
    List<User> findByRoleOrderByNameDesc(Role role);
    List<User> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    Page<User> findByRoleOrderByNameDesc(Role role,Pageable pageable);
    boolean existsByEmail(String email);
    long countByRole(Role role);
    Optional<User> findUserById(Integer id);
}