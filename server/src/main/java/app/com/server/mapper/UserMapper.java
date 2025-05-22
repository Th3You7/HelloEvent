package app.com.server.mapper;


import app.com.server.dtos.UserDTO;
import app.com.server.entity.Admin;
import app.com.server.entity.Client;
import app.com.server.entity.User;
import app.com.server.enums.UserRole;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeforeMapping
    default void addUserRole(User user, @MappingTarget UserDTO dto) {
        if(user instanceof Admin) {
            dto.setUserRole(UserRole.ADMIN);
        }

        if(user instanceof Client) {
            dto.setUserRole(UserRole.CLIENT);
        }
    }

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
