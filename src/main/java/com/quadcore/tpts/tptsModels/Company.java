package com.quadcore.tpts.tptsModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companyDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long companyPlatformId;

    @Column(name = "company_name", length = 200, nullable = false)
    @NotNull(message = "Company name cannot be null")
    private String companyName;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "admin_id")
    private CompanyAdmin admin;


    @Column(name = "company_email", length = 100, nullable = false, unique = true)
    @Email(message = "Enter a valid email format")
    @NotBlank(message = "Company email is required")
    private String companyEmail;

    @Column(name = "company_phone_number", length = 10, nullable = false)
    @NotBlank(message = "Phone number cannot be empty")
    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 digits")
    private String companyPhoneNumber;

    @Column(name = "company_address", columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Company address is required")
    private String companyAddress;

    @Column(name = "city", length = 50)
    @NotBlank(message = "City is required")
    private String city;

    @Column(name = "pincode", length = 6, nullable = false)
    @NotNull(message = "Pincode is required")
    private String pincode;

    @Column(name = "service_areas", columnDefinition = "json")
    private List<String> serviceAreas;

    @Column(name = "gst_number", length = 15, nullable = false)
    @NotBlank(message = "GST number is required")

    private String companyGstNumber;

    @Column(name = "business_license", length = 255, nullable = false)
    @NotBlank(message = "Business license is required")
    private String companyBusinessLicense;

    @Column(name = "base_rate", precision = 10, scale = 2)
    @NotNull(message = "Base rate cannot be null")
    private BigDecimal baseRate;

    @Column(name = "rate_per_km", precision = 10, scale = 2)
    @NotNull(message = "Rate per km is required")
    private BigDecimal ratePerKm;

    @Column(name = "rate_per_kg", precision = 10, scale = 2)
    @NotNull(message = "Rate per kg is required")
    private BigDecimal ratePerKg;

    @Column(name = "eco_vehicle_percentage")
    @NotNull(message = "Eco vehicle percentage is required")
    private Integer ecoVehiclePercentage;

    @Column(name = "average_rating", precision = 3, scale = 2)
    @NotNull(message = "Average rating is required")
    private BigDecimal averageRating;

    @Column(name = "total_deliveries")
    @NotNull(message = "Total deliveries must be provided")
    private Integer totalDeliveries = 0;

    @Column(name = "commission_rate", precision = 5, scale = 2)
    @NotNull(message = "Commission rate cannot be null")
    private BigDecimal commissionRate;


    @OneToMany(mappedBy = "companyItBelongs", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CompanyProducts> companyProudcts;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DeliveryAgent> deliveryAgents;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyStatus status;


    public enum CompanyStatus {
        ACTIVE,
        INACTIVE,
        PENDING,
        SUSPENDED
    }
}
