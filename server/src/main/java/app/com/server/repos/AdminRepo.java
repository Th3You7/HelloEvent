package app.com.server.repos;

import app.com.server.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepo extends JpaRepository<Admin, UUID> {
}
