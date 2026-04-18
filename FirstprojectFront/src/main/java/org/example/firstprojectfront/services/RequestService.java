package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.*;
import org.example.firstprojectfront.repositories.CartItemRepository;
import org.example.firstprojectfront.repositories.PurchaseItemRepository;
import org.example.firstprojectfront.repositories.PurchaseRequestRepository;
import org.example.firstprojectfront.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
    private final PurchaseRequestRepository purchaseRequestRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public RequestService(PurchaseRequestRepository purchaseRequestRepository, PurchaseItemRepository purchaseItemRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.purchaseRequestRepository = purchaseRequestRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public PurchaseRequest submitRequest(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        PurchaseRequest request = new PurchaseRequest();
        request.setUser(user);
        request.setStatus("PENDING");
        request.setCreatedAt(LocalDateTime.now());
        
        PurchaseRequest savedRequest = purchaseRequestRepository.save(request);

        List<PurchaseItem> items = new ArrayList<>();
        for (CartItem ci : cartItems) {
            PurchaseItem pi = new PurchaseItem();
            pi.setPurchaseRequest(savedRequest);
            pi.setProduct(ci.getProduct());
            pi.setQuantity(ci.getQuantity());
            items.add(purchaseItemRepository.save(pi));
        }

        savedRequest.setItems(items);

        // Clear user's cart
        cartItemRepository.deleteByUser(user);

        return savedRequest;
    }

    public List<PurchaseRequest> getAllRequests() {
        return purchaseRequestRepository.findAll();
    }

    public PurchaseRequest updateStatus(Long id, String status) {
        PurchaseRequest req = purchaseRequestRepository.findById(id).orElseThrow();
        req.setStatus(status);
        return purchaseRequestRepository.save(req);
    }
}
