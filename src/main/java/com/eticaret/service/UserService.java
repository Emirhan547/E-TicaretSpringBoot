package com.eticaret.service;

import java.util.List;

import com.eticaret.dto.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUser(Long id);
}