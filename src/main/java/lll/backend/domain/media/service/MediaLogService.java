package lll.backend.domain.media.service;

import lll.backend.common.util.UpdateUtils;
import lll.backend.domain.auth.entity.Member;
import lll.backend.domain.auth.repository.MemberRepository;
import lll.backend.domain.media.dto.request.CreateMediaLogRequest;
import lll.backend.domain.media.dto.request.UpdateMediaLogRequest;
import lll.backend.domain.media.dto.response.MediaLogResponse;
import lll.backend.domain.media.entity.MediaLog;
import lll.backend.domain.media.entity.MediaType;
import lll.backend.domain.media.repository.MediaLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaLogService {

    private final MediaLogRepository mediaLogRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MediaLog createMediaLog(final Long memberId, final CreateMediaLogRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MediaLog mediaLog = MediaLog.builder()
                .member(member)
                .title(request.title())
                .author(request.author())
                .year(request.year())
                .comment(request.comment())
                .mediaType(MediaType.valueOf(request.mediaType()))
                .build();
        mediaLogRepository.save(mediaLog);
        return mediaLog;
    }

    @Transactional(readOnly = true)
    public List<MediaLogResponse> getMediaLogList(final Long memberId) {
        return mediaLogRepository.findAllByMemberId(memberId)
                .stream()
                .map(MediaLogResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public MediaLogResponse getMediaLog(final Long id) {
        MediaLog mediaLog = mediaLogRepository.findById(id).orElseThrow();
        return MediaLogResponse.of(mediaLog);
    }

    @Transactional
    public void updateMediaLog(final Long id, final UpdateMediaLogRequest request) {
        MediaLog mediaLog = mediaLogRepository.findById(id).orElseThrow();
        UpdateUtils.copyNonNullProperties(request, mediaLog);
        mediaLogRepository.save(mediaLog);
    }

    @Transactional
    public void deleteMediaLog(final Long id) {
        MediaLog mediaLog = mediaLogRepository.findById(id).orElseThrow();
        mediaLogRepository.delete(mediaLog);
    }
}
