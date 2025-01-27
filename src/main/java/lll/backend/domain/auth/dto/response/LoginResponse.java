package lll.backend.domain.auth.dto.response;

public record LoginResponse(
        String username,
        String accessToken
) {
}
