package app.com.server.repos;

import app.com.server.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client, UUID> {
}
