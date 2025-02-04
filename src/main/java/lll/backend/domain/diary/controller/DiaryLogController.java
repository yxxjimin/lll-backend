package lll.backend.domain.diary.controller;

import lll.backend.annotation.MemberId;
import lll.backend.domain.diary.dto.request.CreateDiaryLogRequest;
import lll.backend.domain.diary.dto.request.UpdateDiaryLogRequest;
import lll.backend.domain.diary.dto.response.DiaryLogResponse;
import lll.backend.domain.diary.service.DiaryLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log/diary")
@RequiredArgsConstructor
public class DiaryLogController {

    private final DiaryLogService diaryLogService;

    @PostMapping("/new")
    public ResponseEntity<Void> createDiaryLog(
            @MemberId final Long memberId,
            @RequestBody final CreateDiaryLogRequest request
    ) {
        diaryLogService.createDiaryLog(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryLogResponse> getDiaryLogById(
            @PathVariable final Long id
    ) {
        DiaryLogResponse diaryLog = diaryLogService.getDiaryLogById(id);
        return ResponseEntity.ok().body(diaryLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDiaryLog(
            @PathVariable final Long id,
            @RequestBody final UpdateDiaryLogRequest request
    ) {
        diaryLogService.updateDiaryLog(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiaryLog(
            @PathVariable final Long id
    ) {
        diaryLogService.deleteDiaryLog(id);
        return ResponseEntity.ok().build();
    }
}
