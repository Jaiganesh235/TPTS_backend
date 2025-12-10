package com.quadcore.tpts.tptsService;

import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerLoginDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerRegistrationDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerUpdateDto;
import com.quadcore.tpts.tptsModelDTO.CustomerDtoResponse;

public interface CustomerService {

    // Register new customer
    String registerCustomer(CustomerRegistrationDto registrationDto);

    // Login customer
    CustomerDto loginCustomer(CustomerLoginDto loginDto);

    // Fetch all customers with pagination
    CustomerDtoResponse fetchAllCustomers(Integer pageNo, Integer pageSize);

    // Get customer by ID
    CustomerDto getCustomerById(Long customerId);

    // Get customer by email
    CustomerDto getCustomerByEmail(String email);

    // Search customers by name with pagination
    CustomerDtoResponse searchCustomersByName(String name, Integer pageNo, Integer pageSize);

    // Filter customers by city with pagination
    CustomerDtoResponse filterCustomersByCity(String city, Integer pageNo, Integer pageSize);

    // Update customer
    String updateCustomer(CustomerUpdateDto updateDto, Long customerId);

    // Delete customer (soft delete - set isActive to false)
    String deleteCustomer(Long customerId);

    // Activate customer
    String activateCustomer(Long customerId);

    // Deactivate customer
    String deactivateCustomer(Long customerId);
}