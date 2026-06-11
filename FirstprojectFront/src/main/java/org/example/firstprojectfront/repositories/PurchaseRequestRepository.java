package org.example.firstprojectfront.repositories;

import org.example.firstprojectfront.entities.PurchaseRequest;
import org.example.firstprojectfront.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
    List<PurchaseRequest> findByUser(User user);
}
