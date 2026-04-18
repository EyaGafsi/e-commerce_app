package org.example.firstprojectfront.repositories;

import org.example.firstprojectfront.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
