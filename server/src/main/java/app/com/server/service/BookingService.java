package app.com.server.service;

import app.com.server.dtos.BookingDTO;
import app.com.server.entity.Booking;
import app.com.server.mapper.BookingMapper;
import app.com.server.mapper.UserMapper;
import app.com.server.repos.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingService(BookingRepo bookingRepo, BookingMapper bookingMapper) {
        this.bookingRepo = bookingRepo;
        this.bookingMapper = bookingMapper;
    }

    public BookingDTO addBooking(Booking booking) {
        return bookingMapper.toDTO(bookingRepo.save(booking));
    }

    public BookingDTO getBookingId(UUID id) {
        return bookingMapper.toDTO(bookingRepo.findById(id).orElseThrow());
    }

    public Set<BookingDTO> getAllBookings() {
        return new HashSet<>(bookingRepo.findAll().stream().map(bookingMapper::toDTO).toList());
    }

    public Set<BookingDTO> getBookingsByClientId(UUID clientId) {
        return new HashSet<>(bookingRepo.findBookingsByClient_Id(clientId).stream().map(bookingMapper::toDTO).toList());
    }

    public BookingDTO editBooking(Booking booking) {
         Booking bookingToEdit = bookingRepo.findById(booking.getId()).orElseThrow();
         bookingToEdit.setClient(booking.getClient());
         bookingToEdit.setEvent(bookingToEdit.getEvent());

         return bookingMapper.toDTO(bookingRepo.save(bookingToEdit));

    }

    public void deleteBooking(UUID id) {
         bookingRepo.deleteById(id);
    }

}
