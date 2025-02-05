package lll.backend.domain.media.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lll.backend.domain.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "media")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class MediaLog extends BaseEntity {

    @NotNull
    @Setter
    private String title;

    @Setter
    private String author;

    @Setter
    private Integer year;

    @Setter
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
}
