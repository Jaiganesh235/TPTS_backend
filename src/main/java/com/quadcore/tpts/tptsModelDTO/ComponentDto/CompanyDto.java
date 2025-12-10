package com.quadcore.tpts.tptsModelDTO.ComponentDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long companyPlatformId;

    @NotNull(message = "Company name cannot be null")
    private String companyName;

    @NotBlank(message = "Company email is required")
    @Email(message = "Enter a valid email format")
    private String companyEmail;

    @NotBlank(message = "Company phone number cannot be blank")
    @Size(min = 10, max = 12, message = "Phone number must be between 10 and 12 digits")
    private String companyPhoneNumber;

    @NotBlank(message = "City field cannot be blank")
    private String city;

    @NotNull(message = "Pincode cannot be null")
    @Size(min = 6, max = 6, message = "Pincode must be exactly 6 digits")
    private String pincode;

    private List<String> serviceAreas;

    @NotNull(message = "Eco vehicle percentage is required")
    private Integer ecoVehiclePercentage;

    @NotNull(message = "Total deliveries cannot be null")
    private Integer totalDeliveries = 0;
}
