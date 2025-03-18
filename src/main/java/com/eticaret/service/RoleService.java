package com.eticaret.service;

import com.eticaret.entity.Role;
import com.eticaret.entity.RoleType;

public interface RoleService {
	Role findRoleByType(RoleType roleType);
}