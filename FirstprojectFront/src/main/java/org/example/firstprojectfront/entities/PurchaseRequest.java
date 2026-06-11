package org.example.firstprojectfront.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "User reference is required")
    private User user;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PENDING|ACCEPTED|REJECTED)$", message = "Status must be PENDING, ACCEPTED, or REJECTED")
    private String status; // PENDING, ACCEPTED, REJECTED

    @NotNull(message = "Creation date is required")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> items;
}
