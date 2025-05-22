package app.com.server.mapper;

import app.com.server.dtos.BookingDTO;
import app.com.server.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO toDTO(Booking booking);
    Booking toEntity(BookingDTO bookingDTO);
}
