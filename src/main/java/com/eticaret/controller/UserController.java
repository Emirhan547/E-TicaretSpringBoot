package com.eticaret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.dto.UserRequestDTO;
import com.eticaret.dto.UserResponseDTO;
import com.eticaret.entity.User;
import com.eticaret.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private UserService userService;

    // Kullanıcı ekleme
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userDTO) {
	    User createdUser = userService.createUser(userDTO);
	    UserResponseDTO responseDTO = new UserResponseDTO();
	    responseDTO.setId(createdUser.getId());
	    responseDTO.setUsername(createdUser.getUsername());
	    responseDTO.setEmail(createdUser.getEmail());

	    return ResponseEntity.status(201).body(responseDTO);
	}


    // Tüm kullanıcıları listeleme
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Kullanıcıyı ID ile bulma
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Kullanıcıyı güncelleme (Tam güncelleme)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // Kullanıcıyı kısmi güncelleme (Örneğin sadece e-posta değiştirilecekse)
    @PatchMapping("/{id}")
    public ResponseEntity<User> partiallyUpdateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // Kullanıcıyı silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
