package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Role;

import java.util.List;

public interface RoleService {

     List<Role> getAll() ;

     Role getById(Long id);}