package lll.backend.domain.chat.repository;

import lll.backend.domain.chat.entity.ChatResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatResponseRepository extends JpaRepository<ChatResponse, Long> {
}
