package org.example.firstprojectfront.repositories;

import org.example.firstprojectfront.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    long count();
    @Query("SELECT AVG(p.price) FROM Product p")
    Double avgPrice();
}
