package com.eticaret.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    @Column(nullable = false, unique = true)
	    private String email;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	        name = "user_roles",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "role_id")
	    )
	    private Set<Role> roles = new HashSet<>();

	    // Boş yapıcı (JPA için gerekli)
	    public User() {}

	    // Yapıcı (Constructor)
	    public User(String username, String password, String email, Set<Role> roles) {
	        this.username = username;
	        this.password = password;
	        this.email = email;
	        this.roles = roles;
	    }

	    // Getter ve Setter
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getUsername() { return username; }
	    public void setUsername(String username) { this.username = username; }

	    public String getPassword() { return password; }
	    public void setPassword(String password) { this.password = password; }

	    public String getEmail() { return email; }
	    public void setEmail(String email) { this.email = email; }

	    public Set<Role> getRoles() { return roles; }
	    public void setRoles(Set<Role> roles) { this.roles = roles; }

	    // Roles'ü String olarak döndüren yardımcı metot
	    public Set<String> getRoleNames() {
	        return roles.stream()
	                   .map(role -> role.getName().name())
	                   .collect(Collectors.toSet());
	    }
}