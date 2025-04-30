package lll.backend.domain.chat.service;

import lll.backend.domain.auth.repository.MemberRepository;
import lll.backend.domain.chat.dto.request.ChatRequestDto;
import lll.backend.domain.chat.dto.response.ChatResponseDto;
import lll.backend.domain.chat.entity.ChatRequest;
import lll.backend.domain.chat.entity.ChatResponse;
import lll.backend.domain.chat.infra.OpenAIWrapper;
import lll.backend.domain.chat.repository.ChatRequestRepository;
import lll.backend.domain.chat.repository.ChatResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRequestRepository chatMessageRepository;
    private final ChatResponseRepository chatResponseRepository;
    private final MemberRepository memberRepository;
    private final OpenAIWrapper openAIWrapper;

    public ChatResponseDto chat(Long memberId, ChatRequestDto request) {
        ChatRequest chatRequest = ChatRequest.builder()
                .member(memberRepository.findById(memberId).orElseThrow())
                .content(request.message())
                .build();
        chatMessageRepository.save(chatRequest);

        String message = openAIWrapper.getResponseMessage(request.message());
        ChatResponse respMessage = ChatResponse.builder()
                .content(message)
                .chatRequest(chatRequest)
                .build();
        chatResponseRepository.save(respMessage);

        return new ChatResponseDto(message);
    }
}
