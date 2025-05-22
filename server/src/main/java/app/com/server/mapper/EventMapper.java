package app.com.server.mapper;

import app.com.server.dtos.EventDTO;
import app.com.server.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(source = "admin.id", target = "adminId")
    EventDTO toDTO(Event event);
    Event toEntity(EventDTO eventDTO);
}
