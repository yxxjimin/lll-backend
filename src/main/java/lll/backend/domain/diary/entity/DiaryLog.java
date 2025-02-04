package lll.backend.domain.diary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lll.backend.domain.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "diary")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class DiaryLog extends BaseEntity {

    private String content;
}
