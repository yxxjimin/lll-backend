package lll.backend.domain.chat.repository;

import lll.backend.domain.chat.entity.ChatRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
ChatRequestRepository extends JpaRepository<ChatRequest, Long> {
}
