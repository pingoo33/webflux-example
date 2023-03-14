package com.example.domain;

import com.example.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table(name = "tb_user")
@NoArgsConstructor
public class User extends BaseEntity {
    private String email;
    private String password;

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User of(String email, String password) {
        return new User(email, password);
    }
}
