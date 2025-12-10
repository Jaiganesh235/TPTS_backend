package com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDto {

    private String fullName;

    @Email(message = "Enter a valid email format")
    private String email;

    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    private String address;
    private String city;
    private String pincode;
}