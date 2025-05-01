package lll.backend.domain.chat.dto.response;

public record ChatResponseDto(
        Long sessionId,
        String message
) {
}
