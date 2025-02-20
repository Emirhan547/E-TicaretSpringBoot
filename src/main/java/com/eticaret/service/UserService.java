package com.eticaret.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eticaret.dto.UserRequestDTO;
import com.eticaret.entity.User;
import com.eticaret.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service
public class UserService {
	private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Kullanıcı ekleme metodu
    @Transactional
    public User createUser(UserRequestDTO userDTO) {
        return userRepository.save(userDTO);
    }

    // Tüm kullanıcıları getirme metodu
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ID ile kullanıcı getirme metodu
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
    }

    // Kullanıcı güncelleme metodu
    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    // Kullanıcı silme metodu
    @Transactional
    public boolean deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
        return true;
    }
}
