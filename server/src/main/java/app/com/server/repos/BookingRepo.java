package app.com.server.repos;

import app.com.server.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepo extends JpaRepository<Booking, UUID> {
    List<Booking> findBookingsByClient_Id(UUID id);
}
