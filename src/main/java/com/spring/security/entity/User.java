package com.spring.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String id;
    private String pwd;
    private String email;
    private String social_type;
    private String nickname;
    private String phone;
    private String token;
    @CreationTimestamp
    private LocalDateTime reg_dt;
    private LocalDateTime last_login_dt;
    private LocalDateTime lock_dt;
    @UpdateTimestamp
    private LocalDateTime last_update_dt;
    private LocalDateTime withdraw_dt;
    private int pwd_cnt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role"
            , joinColumns = @JoinColumn(name = "user_seq", referencedColumnName = "seq")
            , inverseJoinColumns = @JoinColumn(name = "role_seq", referencedColumnName = "role_seq"))
    private Set<Role> roles;
}
