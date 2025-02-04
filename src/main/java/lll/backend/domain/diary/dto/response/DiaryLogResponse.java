package lll.backend.domain.diary.dto.response;

import lll.backend.domain.diary.entity.DiaryLog;

import java.time.LocalDateTime;

public record DiaryLogResponse(
        Long id,
        Long memberId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {

    public static DiaryLogResponse of(DiaryLog diaryLog) {
        return new DiaryLogResponse(
                diaryLog.getId(),
                diaryLog.getMember().getId(),
                diaryLog.getContent(),
                diaryLog.getCreatedAt(),
                diaryLog.getModifiedAt()
        );
    }
}
