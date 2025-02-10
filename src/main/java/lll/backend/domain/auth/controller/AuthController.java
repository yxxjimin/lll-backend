package lll.backend.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    public LoginResponse signup(@RequestBody final SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    @Operation(summary = "일반 로그인")
    public LoginResponse login(@RequestBody final LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    @Operation(summary = "로그인한 사용자의 ID")
    public Long me(@MemberId final Long memberId) {
        return memberId;
    }
}
