package lll.backend.domain.base.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lll.backend.domain.auth.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    protected Member member;
}
