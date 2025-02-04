package lll.backend.domain.diary.repository;

import lll.backend.domain.auth.entity.Member;
import lll.backend.domain.auth.repository.MemberRepository;
import lll.backend.domain.diary.entity.DiaryLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DiaryLogRepositoryTest {

    @Autowired
    DiaryLogRepository diaryLogRepository;

    @Autowired
    MemberRepository memberRepository;

    Member mockMember = Member.builder()
            .username("test-user")
            .build();

    DiaryLog mockDiary = DiaryLog.builder()
            .content("test diary content")
            .member(mockMember)
            .build();

    @BeforeEach
    void beforeEach() {
        memberRepository.save(mockMember);
        diaryLogRepository.save(mockDiary);
    }

    @Test
    @DisplayName("Member ID로 조회 테스트")
    void findAllByMemberId() {
        List<DiaryLog> findDiaryLog = diaryLogRepository.findAllByMemberId(mockMember.getId());

        assertThat(findDiaryLog).contains(mockDiary);
    }

    @Test
    @DisplayName("날짜로 조회 테스트")
    void findAllByCreatedAtBetween() {
        LocalDateTime start = LocalDateTime.now().minusHours(1);
        LocalDateTime end = LocalDateTime.now();
        List<DiaryLog> findBetween = diaryLogRepository.findAllByCreatedAtBetween(start, end);

        assertThat(findBetween).contains(mockDiary);

        List<DiaryLog> findNone = diaryLogRepository.findAllByCreatedAtBetween(end, end.plusHours(1));

        assertThat(findNone).isNullOrEmpty();
    }
}
