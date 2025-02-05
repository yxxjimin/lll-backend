package lll.backend.domain.media.controller;

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
public class MediaLogController {

    private final MediaLogService mediaLogService;

    @PostMapping
    public ResponseEntity<Void> createMediaLog(
            @MemberId final Long memberId,
            @RequestBody final CreateMediaLogRequest request
    ) {
        MediaLog mediaLog = mediaLogService.createMediaLog(memberId, request);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mediaLog.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<MediaLogResponse>> getMediaLogList(
            @MemberId final Long memberId
    ) {
        List<MediaLogResponse> list = mediaLogService.getMediaLogList(memberId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaLogResponse> getMediaLog(
            @PathVariable final Long id
    ) {
        MediaLogResponse mediaLog = mediaLogService.getMediaLog(id);
        return ResponseEntity.ok(mediaLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMediaLog(
            @PathVariable final Long id,
            @RequestBody final UpdateMediaLogRequest request
    ) {
        mediaLogService.updateMediaLog(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMediaLog(
            @PathVariable final Long id
    ) {
        mediaLogService.deleteMediaLog(id);
        return ResponseEntity.ok().build();
    }
}
