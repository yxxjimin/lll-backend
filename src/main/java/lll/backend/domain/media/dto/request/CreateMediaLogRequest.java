package lll.backend.domain.media.dto.request;

public record CreateMediaLogRequest(
        String title,
        String author,
        Integer year,
        String comment,
        String mediaType
) {
}
