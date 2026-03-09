package org.example.firstprojectfront.repositories;

import org.example.firstprojectfront.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
