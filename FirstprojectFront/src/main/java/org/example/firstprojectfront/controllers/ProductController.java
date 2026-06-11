package org.example.firstprojectfront.controllers;

import jakarta.validation.Valid;
import org.example.firstprojectfront.entities.Product;
import org.example.firstprojectfront.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public Product createProduct(
            @RequestPart("product") @Valid Product product,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            String imageUrl = "http://localhost:8081/uploads/" + fileName;
            product.setImage(imageUrl);
        }

        return productService.save(product);
    }

    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "9") int size) {
        return productService.findPaginatedAndSearched(search, page, size);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public void deleteProductById(@PathVariable long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ADMIN')")
    public Product updateProduct(
            @PathVariable long id,
            @RequestPart("product") @Valid Product product,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            String imageUrl = "http://localhost:8081/uploads/" + fileName;
            product.setImage(imageUrl);
        } else {
            // Keep existing image if no new image uploaded
            Product existing = productService.findById(id);
            product.setImage(existing.getImage());
        }

        return productService.update(id, product);
    }

    @GetMapping("/count")
    public long getProductCount() {
        return productService.count();
    }

    @GetMapping("/avg")
    public double getAvgPrice() {
        return productService.avgPrice();
    }
}
