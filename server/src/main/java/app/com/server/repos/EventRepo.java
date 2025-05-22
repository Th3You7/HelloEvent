package app.com.server.repos;

import app.com.server.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepo extends JpaRepository<Event, UUID> {
}
