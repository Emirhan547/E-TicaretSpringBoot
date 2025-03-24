package com.eticaret.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.eticaret.dto.UserDTO;
import com.eticaret.entity.Role;
import com.eticaret.entity.RoleType;
import com.eticaret.entity.User;

@Mapper(componentModel = "spring") // 🔥 Spring'in otomatik yönetmesi için
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToStringSet")
    UserDTO toDTO(User user);

    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapRolesToRoleSet")
    User toEntity(UserDTO userDTO);

    @Named("mapRolesToStringSet")
    static Set<String> mapRolesToStringSet(Set<Role> roles) {
        return roles == null ? null : roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }

    @Named("mapRolesToRoleSet")
    static Set<Role> mapRolesToRoleSet(Set<String> roleNames) {
        return roleNames == null ? null : roleNames.stream()
                .map(roleName -> new Role(RoleType.valueOf(roleName)))
                .collect(Collectors.toSet());
    }
}