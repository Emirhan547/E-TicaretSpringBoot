package com.eticaret.service.impl;

import org.springframework.stereotype.Service;

import com.eticaret.entity.Role;
import com.eticaret.entity.RoleType;
import com.eticaret.repository.RoleRepository;
import com.eticaret.service.RoleService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByType(RoleType roleType) {
        return roleRepository.findByName(roleType)
                .orElseThrow(() -> new EntityNotFoundException("Rol bulunamadı: " + roleType));
    }
}