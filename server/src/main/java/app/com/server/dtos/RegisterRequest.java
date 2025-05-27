package app.com.server.dtos;

import app.com.server.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private UserRole userRole = UserRole.CLIENT;
}
