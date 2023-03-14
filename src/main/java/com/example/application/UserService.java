package com.example.application;

import com.example.dto.user.SignInRequestDto;
import com.example.dto.user.SignUpRequestDto;
import com.example.domain.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Mono<User> signIn(SignInRequestDto signInRequestDto) {
        Mono<User> user = this.userRepository.findByEmail(signInRequestDto.getEmail())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("이메일을 찾을 수 없습니다.")));

        return user.filter(u -> u.getPassword().equals(signInRequestDto.getPassword()))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("비밀번호가 일치하지 않습니다.")));
    }

    @Transactional
    public Mono<User> signUp(SignUpRequestDto requestDto) {
        userRepository.findByEmail(requestDto.getEmail())
                .doOnNext(u -> {
                    throw new IllegalArgumentException("이미 존재하는 이메일입니다.: " + u.getEmail());
                })
                .subscribe();

        User user = User.of(requestDto.getEmail(), requestDto.getPassword());

        return userRepository.save(user);
    }
}
