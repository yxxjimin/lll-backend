package lll.backend.domain.media.dto.request;

public record UpdateMediaLogRequest(
        String title,
        String author,
        Integer year,
        String comment,
        String moodType
) {
}
