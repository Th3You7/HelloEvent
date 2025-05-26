package app.com.server.controller;

import app.com.server.dtos.AuthRequest;
import app.com.server.dtos.UserDTO;

import app.com.server.entity.Admin;
import app.com.server.entity.User;
import app.com.server.enums.UserRole;
import app.com.server.mapper.UserMapper;
import app.com.server.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/api/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Autowired
    public UserController(AuthenticationManager authenticationManager,
                   JwtUtil jwtTokenUtil,
                   UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }


    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody @Validated AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user instanceof Admin ? UserRole.ADMIN.toString() : UserRole.CLIENT.toString())
                    ));

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtUtil.generateToken(userDetails)
                    )
                    .body(userMapper.toDTO(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
