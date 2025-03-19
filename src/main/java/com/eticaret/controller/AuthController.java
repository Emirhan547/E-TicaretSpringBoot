package com.eticaret.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eticaret.dto.AuthRequestDTO;
import com.eticaret.dto.AuthResponseDTO;
import com.eticaret.dto.UserDTO;
import com.eticaret.entity.Role;
import com.eticaret.entity.RoleType;
import com.eticaret.entity.User;
import com.eticaret.repository.UserRepository;
import com.eticaret.security.jwt.JwtUtil;
import com.eticaret.service.RoleService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        String username = authentication.getName();
        String token = jwtUtil.generateToken(username);

        return ResponseEntity.ok(new AuthResponseDTO(token, "Giriş başarılı!"));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Bu kullanıcı adı zaten alınmış!");
        }

        // Varsayılan rolü (USER) ekleyin
        Role userRole = roleService.findRoleByType(RoleType.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getEmail(), roles);
        userRepository.save(user);

        return ResponseEntity.ok("Kullanıcı başarıyla oluşturuldu!");
    }
}
