package lll.backend.domain.media.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateMediaLogRequest(
        @NotBlank String title,
        String author,
        Integer year,
        String comment,
        String moodType,
        @NotBlank String mediaType
) {
}
