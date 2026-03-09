package org.example.firstprojectfront.entities;

import jakarta.persistence.*;
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
    private String name;
    private double price;
    private String description;
    @ManyToOne
    private Category category;
}
