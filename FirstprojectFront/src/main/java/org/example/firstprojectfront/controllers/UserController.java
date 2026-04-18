package org.example.firstprojectfront.controllers;

import jakarta.validation.Valid;
import org.example.firstprojectfront.entities.User;
import org.example.firstprojectfront.exception.ResourceNotFoundException;
import org.example.firstprojectfront.services.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping({ "/register" })
    public User signup(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return user;
        }

        service.registerNewUser(user);

        return user;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN', 'SUPERADMIN')")
    public List<User> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN', 'SUPERADMIN')")
    public void updateRole(@PathVariable Long id, @RequestParam Long roleId) {
        service.updateRole(id, roleId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN', 'SUPERADMIN')")
    public User getById(@PathVariable Long id) {
        User user = service.getById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN', 'SUPERADMIN')")
    public void create(@Valid @RequestBody User user) {
        service.create(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN', 'SUPERADMIN')")
    public void update(@PathVariable Long id, @Valid @RequestBody User user) {
        user.setId(id);
        service.update(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN', 'SUPERADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/login")
    public User login(
            @RequestParam String email,
            @RequestParam String password) {
        return service.login(email, password);
    }
}
