package com.example.pp_3_1_5_restapi.controllers;

import com.example.pp_3_1_5_restapi.dto.RoleDto;
import com.example.pp_3_1_5_restapi.dto.UserDto;
import com.example.pp_3_1_5_restapi.services.RoleService;
import com.example.pp_3_1_5_restapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.findAllUsers()
                .stream().map(userService::convertToDTO).toList(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        if (userService.findUser(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.convertToDTO(userService.findUser(id).get()), HttpStatus.FOUND);
    }

    @PostMapping("/new")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDTO) {
        userService.save(userService.convertToUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/user/edit/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDTO,
                                          @PathVariable Integer id) {
        userService.update(id, userService.convertToUser(userDTO));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<RoleDto>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAllRoles()
                .stream().map(roleService::convertToRoleDTO).collect(Collectors.toSet()), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
