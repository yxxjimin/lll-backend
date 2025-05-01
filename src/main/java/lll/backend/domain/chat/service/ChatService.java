package lll.backend.domain.chat.service;

import lll.backend.domain.auth.entity.Member;
import lll.backend.domain.auth.repository.MemberRepository;
import lll.backend.domain.chat.dto.request.ChatRequestDto;
import lll.backend.domain.chat.dto.response.ChatResponseDto;
import lll.backend.domain.chat.entity.ChatRequest;
import lll.backend.domain.chat.entity.ChatResponse;
import lll.backend.domain.chat.entity.ChatSession;
import lll.backend.domain.chat.infra.OpenAIWrapper;
import lll.backend.domain.chat.repository.ChatRequestRepository;
import lll.backend.domain.chat.repository.ChatResponseRepository;
import lll.backend.domain.chat.repository.ChatSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRequestRepository chatMessageRepository;
    private final ChatResponseRepository chatResponseRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final MemberRepository memberRepository;
    private final OpenAIWrapper openAIWrapper;

    public ChatResponseDto chat(Long memberId, ChatRequestDto request) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        ChatSession session = getSession(request.sessionId(), member);

        ChatRequest chatRequest = ChatRequest.builder()
                .session(session)
                .content(request.message())
                .build();
        chatMessageRepository.save(chatRequest);

        String message = openAIWrapper.getResponseMessage(request.message());
        ChatResponse respMessage = ChatResponse.builder()
                .content(message)
                .chatRequest(chatRequest)
                .build();
        chatResponseRepository.save(respMessage);

        return new ChatResponseDto(session.getId(), message);
    }

    private ChatSession getSession(Long sessionId, Member member) {
        if (sessionId != null) {
            return chatSessionRepository.findById(sessionId)
                    .map(session -> {
                        if (!session.getMember().getId().equals(member.getId())) {
                            throw new RuntimeException("Forbidden access");
                        }
                        return session;
                    })
                    .orElseThrow(() -> new RuntimeException("Session does not exist"));
        } else {
            return chatSessionRepository.save(
                    ChatSession.builder()
                            .member(member)
                            .build()
            );
        }
    }
}
