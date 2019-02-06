package heapleak.socialnet.repo;

import heapleak.socialnet.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
