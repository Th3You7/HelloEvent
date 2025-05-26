package app.com.server.service;


import app.com.server.dtos.UserDTO;
import app.com.server.entity.Admin;
import app.com.server.entity.User;
import app.com.server.mapper.UserMapper;
import app.com.server.repos.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {
    private final AdminRepo adminRepo;
    private final UserMapper userMapper;


    @Autowired
    public AdminService(AdminRepo adminRepo, UserMapper userMapper) {
        this.adminRepo = adminRepo;
        this.userMapper = userMapper;
    }

    public UserDTO getAdminById(UUID id) {
        return userMapper.toDTO(adminRepo.findById(id).orElseThrow());
    }

    public UserDTO editAdmin(Admin admin) {
        Admin adminToEdit = adminRepo.findById(admin.getId()).orElseThrow();
        adminToEdit.setFirstName(admin.getFirstName());
        adminToEdit.setLastName(admin.getLastName());
        adminToEdit.setPassword(admin.getPassword());
        adminToEdit.setEmail(admin.getEmail());

        return userMapper.toDTO((User)adminRepo.save(adminToEdit));
    }

    public UserDTO createAdmin(Admin admin) {
       return userMapper.toDTO((User) adminRepo.save(admin));
    }


}
