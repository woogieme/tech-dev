package org.c4marathon.api.request;

import lombok.Getter;
import lombok.Setter;
import org.c4marathon.corn.PasswordEncoder;
import org.c4marathon.db.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Getter
@Setter
public class SigninRequest {

    private String name;

    private String password;

    private String userEmail;

    public User toEntity(){
        return User.builder()
                .name(this.name)
                .password(PasswordEncoder.encryptPassword(this.getPassword()))
                .userEmail(this.userEmail)
                .build();
    }
}
