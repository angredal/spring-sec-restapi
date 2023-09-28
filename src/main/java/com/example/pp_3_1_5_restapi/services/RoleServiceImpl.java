package com.example.pp_3_1_5_restapi.services;

import com.example.pp_3_1_5_restapi.dto.RoleDto;
import com.example.pp_3_1_5_restapi.models.Role;
import com.example.pp_3_1_5_restapi.reposetories.RolesReposetories;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RolesReposetories rolesReposetories;
    private final ModelMapper mapper;


    public RoleServiceImpl(RolesReposetories rolesReposetories, ModelMapper mapper) {
        this.rolesReposetories = rolesReposetories;
        this.mapper = mapper;
    }
    @Transactional
    public void saveAll(Set<Role> roles) {
        rolesReposetories.saveAll(roles);
    }
    public Set<Role> findAllRoles() {
        return new HashSet<>(rolesReposetories.findAll());
    }

    @Override
    public Role convertToRole(RoleDto roleDTO) {
        return mapper.map(roleDTO, Role.class);
    }

    @Override
    public RoleDto convertToRoleDTO(Role role) {
        return mapper.map(role, RoleDto.class);
    }

}
