package lll.backend.domain.chat.entity;

import jakarta.persistence.Entity;
import lll.backend.domain.base.entity.BaseUserEntity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
public class ChatRequest extends BaseUserEntity {

    private String content;
}
