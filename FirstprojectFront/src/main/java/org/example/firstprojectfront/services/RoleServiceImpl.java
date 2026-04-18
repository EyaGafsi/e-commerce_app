package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Role;
import org.example.firstprojectfront.repositories.RoleRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RoleServiceImpl implements RoleService {
    private final RoleRepository repo;

    public RoleServiceImpl(RoleRepository repo) {
        this.repo = repo;
    }

    public List<Role> getAll() {
        return repo.findAll();
    }

    public Role getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}