package lll.backend.domain.diary.service;

import lll.backend.domain.auth.entity.Member;
import lll.backend.domain.auth.repository.MemberRepository;
import lll.backend.domain.diary.dto.request.CreateDiaryLogRequest;
import lll.backend.domain.diary.dto.request.UpdateDiaryLogRequest;
import lll.backend.domain.diary.dto.response.DiaryLogResponse;
import lll.backend.domain.diary.entity.DiaryLog;
import lll.backend.domain.diary.repository.DiaryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DiaryLogService {

    private final MemberRepository memberRepository;
    private final DiaryLogRepository diaryLogRepository;

    @Transactional
    public void createDiaryLog(final Long memberId, final CreateDiaryLogRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        DiaryLog diaryLog = DiaryLog.builder()
                .member(member)
                .content(request.content())
                .build();
        diaryLogRepository.save(diaryLog);
    }

    @Transactional(readOnly = true)
    public DiaryLogResponse getDiaryLogById(final Long id) {
        DiaryLog diaryLog = diaryLogRepository.findById(id).orElseThrow();
        return DiaryLogResponse.of(diaryLog);
    }

    @Transactional
    public void updateDiaryLog(final Long id, final UpdateDiaryLogRequest request) {
        DiaryLog diaryLog = diaryLogRepository.findById(id).orElseThrow();
        diaryLog.setContent(request.content());
        diaryLogRepository.save(diaryLog);
    }

    @Transactional
    public void deleteDiaryLog(final Long id) {
        DiaryLog diaryLog = diaryLogRepository.findById(id).orElseThrow();
        diaryLogRepository.delete(diaryLog);
    }
}
