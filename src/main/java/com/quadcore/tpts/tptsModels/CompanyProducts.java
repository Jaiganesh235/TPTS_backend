package com.quadcore.tpts.tptsModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "logistic_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 40)
    @NotBlank(message = "Product name cannot be empty.")
    private String productName;

    private String productDescription;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @ToString.Exclude
    private Company companyItBelongs;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private ProductCategory category;

    @Column(nullable = false)
    @NotNull(message = "Minimum weight is required.")
    @Positive(message = "Minimum weight must be a positive value.")
    private Double minWeight;

    @Column(nullable = false)
    @NotNull(message = "Maximum weight is required.")
    @Positive(message = "Maximum weight must be a positive value.")
    private Double maxWeight;

    private Integer maxDistanceLimit;

    @Column(nullable = false)
    @NotNull(message = "Base price is required.")
    @Positive(message = "Base price must be greater than 0.")
    private Double basePrice;

    private Double discountPercentage;

    private Double finalPrice;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
