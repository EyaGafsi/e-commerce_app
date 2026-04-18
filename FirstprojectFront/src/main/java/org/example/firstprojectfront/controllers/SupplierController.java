package org.example.firstprojectfront.controllers;

import org.example.firstprojectfront.entities.Supplier;
import org.example.firstprojectfront.services.SupplierService;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }
    
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN', 'ROLE_USER', 'USER')")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN', 'ROLE_USER', 'USER')")
    public Supplier getSupplierById(@PathVariable long id) {
        return supplierService.getSupplierById(id);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public void deleteSupplierById(@PathVariable long id) {
        supplierService.deleteSupplierById(id);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public Supplier updateSupplier(@PathVariable long id, @RequestBody Supplier supplier){
        return supplierService.updateSupplier(id,supplier);
    }

}
