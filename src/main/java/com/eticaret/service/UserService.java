package com.eticaret.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eticaret.dto.UserRequestDTO;
import com.eticaret.entity.User;
import com.eticaret.exception.UserNotFoundException;
import com.eticaret.repository.UserRepository;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequestDTO userRequestDTO) {
        logger.info("Yeni kullanıcı oluşturuluyor: {}", userRequestDTO.getUsername());

        // DTO'yu Entity'ye çevir
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword()); // Hashleme eklenebilir!
        user.setEmail(userRequestDTO.getEmail());

        User savedUser = userRepository.save(user);
        logger.info("Kullanıcı başarıyla oluşturuldu: {}", savedUser.getId());

        return savedUser;
    }


    public List<User> getAllUsers() {
        logger.info("Tüm kullanıcılar listeleniyor...");
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        logger.info("Kullanıcı ID ile aranıyor: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı bulunamadı! ID: " + id));
    }


    public User updateUser(Long id, User updatedUser) {
        logger.info("Kullanıcı güncelleniyor: ID: {}", id);
        User existingUser = getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());

        User savedUser = userRepository.save(existingUser);
        logger.info("Kullanıcı güncellendi: ID: {}", id);
        return savedUser;
    }

    public boolean deleteUser(Long id) {
        logger.info("Kullanıcı siliniyor: ID: {}", id);
        User user = getUserById(id);
        userRepository.delete(user);
        logger.info("Kullanıcı başarıyla silindi: ID: {}", id);
        return true;
    }
	
}
