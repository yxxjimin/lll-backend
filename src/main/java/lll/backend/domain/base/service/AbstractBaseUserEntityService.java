package lll.backend.domain.base.service;

import lll.backend.common.exception.NotFoundException;
import lll.backend.domain.base.entity.BaseUserEntity;
import lll.backend.domain.base.repository.BaseUserEntityRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public abstract class AbstractBaseUserEntityService<T extends BaseUserEntity, R extends BaseUserEntityRepository<T>> {

    protected final R repository;

    public T getById(final Long id, final Long memberId) {
        T entity = repository.findById(id).orElseThrow();
        if (!Objects.equals(entity.getMember().getId(), memberId)) {
            throw new NotFoundException(String.format("리소스 ID %d에 접근할 수 없습니다", id));
        }
        return entity;
    }
}
