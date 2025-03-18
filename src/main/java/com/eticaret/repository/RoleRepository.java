package com.eticaret.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eticaret.entity.Role;
import com.eticaret.entity.RoleType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
	Optional<Role> findByName(RoleType name);

    default Optional<Role> findByType(RoleType roleType) {
        return findByName(roleType);
    }
}