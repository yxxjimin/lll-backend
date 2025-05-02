package lll.backend.domain.base.repository;

import lll.backend.domain.base.entity.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface BaseUserEntityRepository<T extends BaseUserEntity> extends JpaRepository<T, Long>{

    List<T> findAllByMemberId(Long memberId);

    List<T> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}