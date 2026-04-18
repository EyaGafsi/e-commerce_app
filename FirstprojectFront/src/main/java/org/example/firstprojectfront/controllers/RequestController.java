package org.example.firstprojectfront.controllers;

import org.example.firstprojectfront.entities.PurchaseRequest;
import org.example.firstprojectfront.services.RequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'USER')")
    public PurchaseRequest submitRequest(Principal principal) {
        return requestService.submitRequest(principal.getName());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public List<PurchaseRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public PurchaseRequest updateStatus(@PathVariable Long id, @RequestParam String status) {
        return requestService.updateStatus(id, status);
    }
}
