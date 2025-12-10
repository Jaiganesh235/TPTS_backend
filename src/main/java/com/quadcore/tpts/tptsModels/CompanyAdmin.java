package com.quadcore.tpts.tptsModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "company_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Admin name cannot be empty.")
    private String CompanyAdminName;

    @NotBlank(message = "Admin position is required.")
    private String CompanyAdminPostion;

    @NotBlank(message = "Office ID number is required.")
    private String CompanyAdminOfficeIdNumber;

    @NotBlank(message = "Admin email cannot be empty.")
    @Email(message = "Please provide a valid email address for the admin.")
    private String CompanyAdminEmail;

    @Column(name = "password", length = 255, nullable = false)
    @NotBlank(message = "Password cannot be empty")
    @ToString.Exclude
    private String password;

    @NotBlank(message = "Admin phone number cannot be empty.")
    @Size(min = 10, max = 10, message = "Admin phone number must be exactly 10 digits.")
    private String CompanyAdminPhoneNo;

    @OneToOne(mappedBy = "admin")
    private Company organization;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
