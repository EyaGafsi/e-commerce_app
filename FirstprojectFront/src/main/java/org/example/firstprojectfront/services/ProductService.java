package org.example.firstprojectfront.services;

import org.example.firstprojectfront.entities.Product;
import org.example.firstprojectfront.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }
    public Product update(Long id,Product p) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product=productOptional.get();
            product.setPrice(p.getPrice());
            product.setDescription(p.getDescription());
            product.setCategory(p.getCategory());
            product.setName(p.getName());
            product.setSupplier(p.getSupplier());
            product.setImage(p.getImage());
            return productRepository.save(product);
        }
        else return null;
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
    public long count() {
        return productRepository.count();
    }
    public double avgPrice() {
        return productRepository.avgPrice();
    }

}
