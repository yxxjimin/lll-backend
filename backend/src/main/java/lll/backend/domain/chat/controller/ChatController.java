package lll.backend.domain.chat.controller;

import lll.backend.common.annotation.MemberId;
import lll.backend.domain.chat.dto.request.ChatRequestDto;
import lll.backend.domain.chat.dto.response.ChatResponseDto;
import lll.backend.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/new")
    public ChatResponseDto chat(
            @MemberId Long memberId,
            @RequestBody final ChatRequestDto request
    ) {
        return chatService.chat(memberId, request);
    }
}
