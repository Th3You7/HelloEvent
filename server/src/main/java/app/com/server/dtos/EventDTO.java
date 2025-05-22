package app.com.server.dtos;

import app.com.server.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private UUID id;
    private EventType eventType;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID adminId;
    private Set<BookingDTO> bookingDTOS;
}
