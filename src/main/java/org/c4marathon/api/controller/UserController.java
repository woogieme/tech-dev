package org.c4marathon.api.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.c4marathon.api.request.SigninRequest;
import org.c4marathon.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signin")
    private void signin (@RequestBody SigninRequest signinRequest){
        userService.signin(signinRequest);
    }

    //로그인

    //로그아웃
}
