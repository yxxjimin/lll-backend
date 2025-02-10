package lll.backend.domain.media.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lll.backend.annotation.MemberId;
import lll.backend.domain.media.dto.request.CreateMediaLogRequest;
import lll.backend.domain.media.dto.request.UpdateMediaLogRequest;
import lll.backend.domain.media.dto.response.MediaLogResponse;
import lll.backend.domain.media.entity.MediaLog;
import lll.backend.domain.media.service.MediaLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/log/media")
@RequiredArgsConstructor
@Tag(name = "Media Logging API")
public class MediaLogController {

    private final MediaLogService mediaLogService;

    @PostMapping
    @Operation(summary = "새 미디어 로그 생성")
    public ResponseEntity<Void> createMediaLog(
            @MemberId final Long memberId,
            @RequestBody @Valid final CreateMediaLogRequest request
    ) {
        MediaLog mediaLog = mediaLogService.createMediaLog(memberId, request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mediaLog.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @Operation(summary = "사용자의 전체 미디어 로그 목록 조회")
    public ResponseEntity<List<MediaLogResponse>> getMediaLogList(
            @MemberId final Long memberId
    ) {
        List<MediaLogResponse> list = mediaLogService.getMediaLogList(memberId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 미디어 로그 조회")
    public ResponseEntity<MediaLogResponse> getMediaLog(
            @PathVariable final Long id,
            @MemberId final Long memberId
    ) {
        MediaLogResponse mediaLog = mediaLogService.getMediaLog(id, memberId);
        return ResponseEntity.ok(mediaLog);
    }

    @PutMapping("/{id}")
    @Operation(summary = "미디어 로그 수정")
    public ResponseEntity<Void> updateMediaLog(
            @PathVariable final Long id,
            @MemberId final Long memberId,
            @RequestBody final UpdateMediaLogRequest request
    ) {
        mediaLogService.updateMediaLog(id, memberId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "미디어 로그 삭제")
    public ResponseEntity<Void> deleteMediaLog(
            @PathVariable final Long id,
            @MemberId final Long memberId
    ) {
        mediaLogService.deleteMediaLog(id, memberId);
        return ResponseEntity.ok().build();
    }
}
