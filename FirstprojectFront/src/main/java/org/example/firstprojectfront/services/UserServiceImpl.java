package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Role;
import org.example.firstprojectfront.entities.RoleName;
import org.example.firstprojectfront.entities.User;
import org.example.firstprojectfront.exception.DuplicateResourceException;
import org.example.firstprojectfront.repositories.RoleRepository;
import org.example.firstprojectfront.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll().stream()
                .filter(u -> u.getRole() == null || u.getRole().getName() == null || 
                             (!u.getRole().getName().toString().equals("SUPERADMIN") &&
                              !u.getRole().getName().toString().equals("ROLE_SUPERADMIN")))
                .toList();
    }

    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void create(User user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new DuplicateResourceException("Username already taken");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already taken");
        }
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        repo.save(user);
    }

    public void update(User user) {
        repo.save(user);
    }

    public void delete(Long id) {
        repo.findById(id).ifPresent(repo::delete);
    }

    @Override
    public User registerNewUser(User user) {
        if (repo.existsByUsername(user.getUsername())) {
            throw new DuplicateResourceException("Username already taken");
        }
        if (repo.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("Email already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() != null && user.getRole().getName() != null) {
            Role existingRole = roleRepo.findByName(user.getRole().getName());
            if (existingRole != null) {
                user.setRole(existingRole);
            }
        }
        if (user.getRole() == null) {
            Role userRole = roleRepo.findByName(RoleName.valueOf("USER"));
            user.setRole(userRole);
        }

        return repo.save(user);
    }

    public User login(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    @Override
    public void updateRole(Long userId, Long roleId) {
        User user = repo.findById(userId).orElseThrow(() -> new org.example.firstprojectfront.exception.ResourceNotFoundException("User not found"));
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new org.example.firstprojectfront.exception.ResourceNotFoundException("Role not found"));
        
        // Prevent altering another SUPERADMIN
        if (user.getRole() != null && user.getRole().getName() != null &&
            (user.getRole().getName().toString().equals("SUPERADMIN") || user.getRole().getName().toString().equals("ROLE_SUPERADMIN"))) {
            throw new RuntimeException("Cannot modify another SuperAdmin");
        }
        
        // Prevent setting role to SUPERADMIN (assuming 4 = SUPERADMIN)
        if (role.getName() != null && (role.getName().toString().equals("SUPERADMIN") || role.getName().toString().equals("ROLE_SUPERADMIN"))) {
            throw new RuntimeException("Cannot grant SuperAdmin privileges");
        }
        
        user.setRole(role);
        repo.save(user);
    }
}
