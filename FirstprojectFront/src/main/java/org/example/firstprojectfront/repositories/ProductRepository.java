package org.example.firstprojectfront.repositories;

import org.example.firstprojectfront.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
