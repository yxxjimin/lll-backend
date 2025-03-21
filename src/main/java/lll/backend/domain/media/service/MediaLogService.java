package lll.backend.domain.media.service;

import lll.backend.common.util.UpdateUtils;
import lll.backend.domain.auth.entity.Member;
import lll.backend.domain.auth.repository.MemberRepository;
import lll.backend.domain.base.service.AbstractBaseEntityService;
import lll.backend.domain.media.dto.request.CreateMediaLogRequest;
import lll.backend.domain.media.dto.request.UpdateMediaLogRequest;
import lll.backend.domain.media.dto.response.MediaLogResponse;
import lll.backend.domain.media.entity.MediaLog;
import lll.backend.domain.media.entity.MediaType;
import lll.backend.domain.media.repository.MediaLogRepository;
import lll.backend.domain.mood.entity.MoodType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MediaLogService extends AbstractBaseEntityService<MediaLog, MediaLogRepository> {

    private final MemberRepository memberRepository;

    public MediaLogService(MediaLogRepository repository, MemberRepository memberRepository) {
        super(repository);
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MediaLog createMediaLog(final Long memberId, final CreateMediaLogRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MediaLog mediaLog = MediaLog.builder()
                .member(member)
                .title(request.title())
                .author(request.author())
                .year(request.year())
                .comment(request.comment())
                .moodType(MoodType.valueOf(request.moodType()))
                .mediaType(MediaType.valueOf(request.mediaType()))
                .build();
        repository.save(mediaLog);
        return mediaLog;
    }

    @Transactional(readOnly = true)
    public List<MediaLogResponse> getMediaLogList(final Long memberId) {
        return repository.findAllByMemberId(memberId)
                .stream()
                .map(MediaLogResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public MediaLogResponse getMediaLog(final Long id, final Long memberId) {
        MediaLog mediaLog = getById(id, memberId);
        return MediaLogResponse.of(mediaLog);
    }

    @Transactional
    public void updateMediaLog(final Long id, final Long memberId, final UpdateMediaLogRequest request) {
        MediaLog mediaLog = getById(id, memberId);
        UpdateUtils.copyNonNullProperties(request, mediaLog);
        repository.save(mediaLog);
    }

    @Transactional
    public void deleteMediaLog(final Long id, final Long memberId) {
        MediaLog mediaLog = getById(id, memberId);
        repository.delete(mediaLog);
    }
}
