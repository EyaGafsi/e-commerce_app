package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Product;
import org.example.firstprojectfront.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public void delete(Product product) {
        productRepository.delete(product);
    }
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
