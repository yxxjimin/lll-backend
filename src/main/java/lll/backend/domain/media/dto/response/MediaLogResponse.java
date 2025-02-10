package lll.backend.domain.media.dto.response;

import lll.backend.domain.media.entity.MediaLog;

import java.time.LocalDateTime;
import java.util.Optional;

public record MediaLogResponse(
        String title,
        String author,
        Integer year,
        String comment,
        String moodType,
        String mediaType,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static MediaLogResponse of(MediaLog mediaLog) {
        return new MediaLogResponse(
                mediaLog.getTitle(),
                mediaLog.getAuthor(),
                mediaLog.getYear(),
                mediaLog.getComment(),
                Optional.ofNullable(mediaLog.getMoodType())
                        .map(Object::toString)
                        .orElse(null),
                mediaLog.getMediaType().toString(),
                mediaLog.getCreatedAt(),
                mediaLog.getModifiedAt()
        );
    }
}
