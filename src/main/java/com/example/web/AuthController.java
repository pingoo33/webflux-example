package com.example.web;

import com.example.domain.User;
import com.example.dto.user.SignInRequestDto;
import com.example.dto.user.SignUpRequestDto;
import com.example.application.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-in")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<User> signIn(
            @RequestBody @Valid SignInRequestDto signInRequestDto
    ) {
        return this.userService.signIn(signInRequestDto);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Mono<User> signUp(
            @RequestBody @Valid SignUpRequestDto signUpRequestDto
    ) {
        return this.userService.signUp(signUpRequestDto);
    }
}
