package com.eticaret.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eticaret.domain.UserDomainService;
import com.eticaret.dto.UserDTO;
import com.eticaret.entity.Role;
import com.eticaret.entity.RoleType;
import com.eticaret.entity.User;
import com.eticaret.mapper.UserMapper;
import com.eticaret.repository.UserRepository;
import com.eticaret.service.EmailService;
import com.eticaret.service.RoleService;
import com.eticaret.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDomainService userDomainService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(
            UserDomainService userDomainService,
            RoleService roleService,
            PasswordEncoder passwordEncoder,
            EmailService emailService,
            UserRepository userRepository,
            UserMapper userMapper) {
        this.userDomainService = userDomainService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;  // ✅ Mapper artık enjekte edildi!
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Yeni kullanıcı oluşturuluyor: {}", userDTO.getUsername());

        Role userRole = roleService.findRoleByType(RoleType.USER);
        Set<Role> roles = Set.of(userRole);

        User user = userMapper.toEntity(userDTO); // ✅ HATA DÜZELTİLDİ
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roles);
        user = userDomainService.saveUser(user);

        logger.info("Kullanıcı başarıyla oluşturuldu: {}", user.getUsername());

        sendWelcomeEmail(user.getEmail(), user.getUsername());

        return userMapper.toDTO(user);
    }

    @Async
    private void sendWelcomeEmail(String email, String username) {
        logger.info("Hoş geldiniz e-postası gönderiliyor...");
        String subject = "Aramıza Hoş Geldin!";
        String body = "Merhaba " + username + ",\n\nSitemize kayıt olduğun için teşekkür ederiz! Keyifli alışverişler dileriz.";
        emailService.sendEmail(email, subject, body);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserDTO getUserById(Long id) {
        logger.info("Kullanıcı getiriliyor. ID: {}", id);
        Optional<User> userOptional = userRepository.getUserWithRolesById(id);
        if (userOptional.isPresent()) {
            return userMapper.toDTO(userOptional.get()); // ✅ Kod sadeleştirildi!
        } else {
            throw new EntityNotFoundException("Kullanıcı bulunamadı: " + id);
        }
    }

    @Override
    @Cacheable(value = "users")
    public List<UserDTO> getAllUsers() {
        logger.info("Tüm kullanıcılar getiriliyor...");
        return userDomainService.getAllUsers().stream()
                .map(userMapper::toDTO) // ✅ Daha temiz kod!
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        logger.warn("Kullanıcı siliniyor! ID: {}", id);
        userDomainService.deleteUser(id);
        logger.info("Kullanıcı başarıyla silindi. ID: {}", id);
    }
} 