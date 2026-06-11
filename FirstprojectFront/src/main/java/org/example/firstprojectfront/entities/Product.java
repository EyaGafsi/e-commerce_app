package org.example.firstprojectfront.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
   @GeneratedValue(
           strategy = GenerationType.IDENTITY
  )
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be strictly positive")
    private double price;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Size(max = 500, message = "Image path is too long")
    private String image;

    @ManyToOne
    @NotNull(message = "Category is required")
    private Category category;

    @ManyToOne
    @NotNull(message = "Supplier is required")
    private Supplier supplier;
}
