package org.example.firstprojectfront.repositories;

import org.example.firstprojectfront.entities.CartItem;
import org.example.firstprojectfront.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
}
