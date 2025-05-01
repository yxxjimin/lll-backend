package lll.backend.domain.chat.dto.request;

public record ChatRequestDto(
        Long sessionId,
        String message
) {
}
