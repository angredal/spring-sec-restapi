package com.example.pp_3_1_5_restapi.services;

import com.example.pp_3_1_5_restapi.dto.RoleDto;
import com.example.pp_3_1_5_restapi.models.Role;

import java.util.Set;

public interface RoleService {
    void saveAll(Set<Role> roles);
    Set<Role> findAllRoles();

    Role convertToRole(RoleDto roleDTO);

    RoleDto convertToRoleDTO(Role role);
}
