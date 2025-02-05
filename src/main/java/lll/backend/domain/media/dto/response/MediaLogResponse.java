package lll.backend.domain.media.dto.response;

import lll.backend.domain.media.entity.MediaLog;

import java.time.LocalDateTime;

public record MediaLogResponse(
        String title,
        String author,
        Integer year,
        String comment,
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
                mediaLog.getMediaType().toString(),
                mediaLog.getCreatedAt(),
                mediaLog.getModifiedAt()
        );
    }
}
