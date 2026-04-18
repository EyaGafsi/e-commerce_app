package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Category;
import org.example.firstprojectfront.entities.Supplier;
import org.example.firstprojectfront.repositories.CategoryRepository;
import org.example.firstprojectfront.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupplierService  {
    private SupplierRepository supplierRepository;
public SupplierService(SupplierRepository supplierRepository) {
    this.supplierRepository = supplierRepository;
}
public Supplier createSupplier(Supplier supplier) {
    return supplierRepository.save(supplier);

}
public Supplier getSupplierById(Long id) {
    return supplierRepository.findById(id).orElse(null);
}
public List<Supplier> getAllSuppliers() {
    return supplierRepository.findAll();
}
public void deleteSupplierById(Long id) {
    supplierRepository.deleteById(id);
}
public Supplier updateSupplier(Long id, Supplier s) {
    Optional<Supplier> supplierOptional = supplierRepository.findById(id);
    if (supplierOptional.isPresent()) {
        Supplier supplier=supplierOptional.get();
        supplier.setName(s.getName());
        supplier.setAddress(s.getAddress());
        supplier.setPhone(s.getPhone());
        supplier.setEmail(s.getEmail());

        return supplierRepository.save(supplier);
    }
    else return null;
}

}
