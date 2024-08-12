package org.c4marathon.api.service;

import lombok.RequiredArgsConstructor;
import org.c4marathon.api.request.SigninRequest;
import org.c4marathon.db.entity.User;
import org.c4marathon.db.repository.UserRepsotory;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepsotory userRepsotory;

    public void signin(SigninRequest signinRequest)  {

        //아이디(이메일) 확인
        validateId(signinRequest.getUserEmail());

        //비밀번호암호화
        User user = signinRequest.toEntity();

        userRepsotory.save(user);

    }

    private void validateId(String userEmail) {
        userRepsotory.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
