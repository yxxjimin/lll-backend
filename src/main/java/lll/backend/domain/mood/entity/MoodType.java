package lll.backend.domain.mood.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MoodType {
    MN001("화나는"),
    MN002("두려운"),
    MN003("짜증나는"),
    MN004("긴장되는"),
    MN005("답답한"),
    MN006("불안한"),
    MO007("그저 그런"),
    MO008("그리운"),
    MP009("신나는"),
    MP010("열정적인")
    ;

    private final String title;
}
