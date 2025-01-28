package lll.backend.domain.auth.controller;

import lll.backend.annotation.MemberId;
import lll.backend.domain.auth.dto.request.LoginRequest;
import lll.backend.domain.auth.dto.request.SignupRequest;
import lll.backend.domain.auth.dto.response.LoginResponse;
import lll.backend.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public LoginResponse signup(@RequestBody final SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody final LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public Long me(@MemberId final Long memberId) {
        return memberId;
    }
}
