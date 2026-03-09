package org.example.firstprojectfront.controllers;

import org.example.firstprojectfront.entities.Product;
import org.example.firstprojectfront.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
   @PostMapping
   public Product createProduct(

           @RequestBody Product product
   ) {
       return productService.save(product);
   }
   @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
   }
   @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.findById(id);
   }
   @DeleteMapping
    public void deleteProductById(@PathVariable long id) {
        productService.deleteById(id);
   }
   @PutMapping
    public Product updateProduct(
            @RequestBody Product product
   ){
        return productService.save(product);
   }
}
