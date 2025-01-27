package lll.backend.domain.auth.service;

import lll.backend.domain.auth.dto.request.LoginRequest;
import lll.backend.domain.auth.dto.request.SignupRequest;
import lll.backend.domain.auth.dto.response.LoginResponse;
import lll.backend.domain.auth.entity.Member;
import lll.backend.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse signup(final SignupRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        Member member = Member.builder()
                .username(request.username())
                .password(encodedPassword)
                .build();
        memberRepository.save(member);

        return new LoginResponse(
                member.getUsername(),
                "fake-signup-token"
        );
    }

    public LoginResponse login(final LoginRequest request) {
        final Member member = memberRepository.findByUsername(request.username()).orElseThrow();
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }
        return new LoginResponse(
                member.getUsername(),
                "fake-login-token"
        );
    }
}
