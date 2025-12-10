package com.quadcore.tpts.tptsModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_platform_id", nullable = false)
    private Company company;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false, length = 255)
    private String password; // hashed value

    @Column(nullable = false, length = 15)
    @NotBlank
    private String phoneNumber;

    @Column(length = 50)
    @NotBlank
    private String vehicleType;

    @Column(length = 20)
    @NotBlank
    private String vehicleNumber;

    @Column(length = 30)
    @NotBlank
    private String licenseNumber;


    private Boolean isAvailable = true;

    @Column(length = 20)
    private String role = "DELIVERY_AGENT";

    private Boolean isActive = true;

    @Column(precision = 3, scale = 2)
    private BigDecimal avgRating = BigDecimal.valueOf(0.00);

    private Integer totalDeliveries = 0;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

