package app.com.server.service;


import app.com.server.dtos.UserDTO;
import app.com.server.entity.Client;
import app.com.server.mapper.UserMapper;
import app.com.server.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepo clientRepo;
    private final UserMapper userMapper;

    @Autowired
    public ClientService(ClientRepo clientRepo, UserMapper userMapper) {
        this.clientRepo = clientRepo;
        this.userMapper = userMapper;
    }

    public Set<UserDTO> getAllClients() {
        return new HashSet<>(clientRepo.findAll().stream().map(userMapper::toDTO).toList());
    }

    public UserDTO getClientById(UUID clientId) {
        return userMapper.toDTO(clientRepo.findById(clientId).orElseThrow());
    }
}
