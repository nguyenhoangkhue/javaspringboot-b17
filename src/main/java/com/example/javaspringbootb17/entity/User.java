package com.example.javaspringbootb17.entity;
import com.example.javaspringbootb17.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(unique = true)
    String email;

    @Column()
    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String avatar;
}
