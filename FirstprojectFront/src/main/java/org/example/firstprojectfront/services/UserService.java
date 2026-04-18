package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.User;

import java.util.List;

public interface UserService {

     List<User> getAll();

     User getById(Long id);

     void create(User user);

     void update(User user);

     void delete(Long id);

     User registerNewUser(User user);

     String getEncodedPassword(String password);

     User login(String email, String password);

     User findByUsername(String username);

     void updateRole(Long userId, Long roleId);
}
