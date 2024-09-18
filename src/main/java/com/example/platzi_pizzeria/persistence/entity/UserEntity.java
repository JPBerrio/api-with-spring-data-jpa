package com.example.platzi_pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "benutzer")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean disabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;
}
