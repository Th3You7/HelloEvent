package app.com.server.service;


import app.com.server.dtos.EventDTO;
import app.com.server.entity.Booking;
import app.com.server.entity.Event;
import app.com.server.mapper.EventMapper;
import app.com.server.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class EventService {
    private final EventRepo eventRepo;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventMapper eventMapper, EventRepo eventRepo) {
        this.eventRepo = eventRepo;
        this.eventMapper = eventMapper;
    }

    public EventDTO addEvent(Event event){
        return eventMapper.toDTO(eventRepo.save(event));
    }

    public EventDTO getEventByID(UUID id) {
        return eventMapper.toDTO(eventRepo.findById(id).orElseThrow());
    }

    public Set<EventDTO> getAllEvents() {
        return new HashSet<>(eventRepo.findAll().stream().map(eventMapper::toDTO).toList());
    }

    public EventDTO editEvent(Event event) {
        Event event1 = eventRepo.findById(event.getId()).orElseThrow();
        event1.setEventType(event.getEventType());
        event1.setDescription(event.getDescription());
        event1.setStartDate(event.getStartDate());
        event1.setEndDate(event.getEndDate());

        return eventMapper.toDTO(eventRepo.save(event1));
    }

    public void deleteEventById(UUID id) {
        eventRepo.deleteById(id);
    }
}
