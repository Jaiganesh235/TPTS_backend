package com.quadcore.tpts.tptsServiceImpl;

import com.quadcore.tpts.tptsExceptions.CustomerAlreadyExistsException;
import com.quadcore.tpts.tptsExceptions.CustomerInactiveException;
import com.quadcore.tpts.tptsExceptions.CustomerNotFoundException;
import com.quadcore.tpts.tptsExceptions.InvalidCredentialsException;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerLoginDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerRegistrationDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerUpdateDto;
import com.quadcore.tpts.tptsModelDTO.CustomerDtoResponse;
import com.quadcore.tpts.tptsModels.Customer;
import com.quadcore.tpts.tptsRepository.CustomerRepository;
import com.quadcore.tpts.tptsService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Helper method: Convert Customer entity to CustomerDto
    private CustomerDto mapToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setAddress(customer.getAddress());
        dto.setCity(customer.getCity());
        dto.setPincode(customer.getPincode());
        dto.setIsActive(customer.getIsActive());
        dto.setCreatedAt(customer.getCreatedAt());
        dto.setUpdatedAt(customer.getUpdatedAt());
        return dto;
    }

    // Helper method: Create CustomerDtoResponse from Page
    private CustomerDtoResponse createDtoResponse(Page<Customer> customerPage) {
        List<CustomerDto> content = customerPage.getContent()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        CustomerDtoResponse response = new CustomerDtoResponse();
        response.setContent(content);
        response.setPageNo(customerPage.getNumber());
        response.setPageSize(customerPage.getSize());
        response.setTotalElements(customerPage.getTotalElements());
        response.setTotalPages(customerPage.getTotalPages());
        response.setLast(customerPage.isLast());
        return response;
    }

    @Override
    public String registerCustomer(CustomerRegistrationDto registrationDto) {
        // Check if email already exists
        if (customerRepository.existsByEmail(registrationDto.getEmail())) {
            throw new CustomerAlreadyExistsException(
                    "Customer with email " + registrationDto.getEmail() + " already exists"
            );
        }

        // Create new customer
        Customer customer = new Customer();
        customer.setFullName(registrationDto.getFullName());
        customer.setEmail(registrationDto.getEmail());
        customer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        customer.setPhoneNumber(registrationDto.getPhoneNumber());
        customer.setAddress(registrationDto.getAddress());
        customer.setCity(registrationDto.getCity());
        customer.setPincode(registrationDto.getPincode());
        customer.setIsActive(true);
        customer.setRole("CUSTOMER");

        customerRepository.save(customer);
        return "Customer registered successfully with email: " + customer.getEmail();
    }

    @Override
    public CustomerDto loginCustomer(CustomerLoginDto loginDto) {
        Customer customer = customerRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with email: " + loginDto.getEmail()
                ));

        if (!customer.getIsActive()) {
            throw new CustomerInactiveException(
                    "Customer account is inactive. Please contact support."
            );
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), customer.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return mapToDto(customer);
    }

    @Override
    public CustomerDtoResponse fetchAllCustomers(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return createDtoResponse(customerPage);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + customerId
                ));
        return mapToDto(customer);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with email: " + email
                ));
        return mapToDto(customer);
    }

    @Override
    public CustomerDtoResponse searchCustomersByName(String name, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("fullName").ascending());
        Page<Customer> customerPage = customerRepository.findByFullNameContainingIgnoreCase(name, pageable);
        return createDtoResponse(customerPage);
    }

    @Override
    public CustomerDtoResponse filterCustomersByCity(String city, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        Page<Customer> customerPage = customerRepository.findByCityIgnoreCase(city, pageable);
        return createDtoResponse(customerPage);
    }

    @Override
    @Transactional
    public String updateCustomer(CustomerUpdateDto updateDto, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + customerId
                ));


        if (updateDto.getFullName() != null) {
            customer.setFullName(updateDto.getFullName());
        }
        if (updateDto.getEmail() != null) {

            if (!customer.getEmail().equals(updateDto.getEmail()) &&
                    customerRepository.existsByEmail(updateDto.getEmail())) {
                throw new CustomerAlreadyExistsException(
                        "Email " + updateDto.getEmail() + " is already in use"
                );
            }
            customer.setEmail(updateDto.getEmail());
        }
        if (updateDto.getPhoneNumber() != null) {
            customer.setPhoneNumber(updateDto.getPhoneNumber());
        }
        if (updateDto.getAddress() != null) {
            customer.setAddress(updateDto.getAddress());
        }
        if (updateDto.getCity() != null) {
            customer.setCity(updateDto.getCity());
        }
        if (updateDto.getPincode() != null) {
            customer.setPincode(updateDto.getPincode());
        }

        customerRepository.save(customer);
        return "Customer updated successfully with ID: " + customerId;
    }

    @Override
    public String deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + customerId
                ));

        // Soft delete - set isActive to false
        customer.setIsActive(false);
        customerRepository.save(customer);
        return "Customer deactivated successfully with ID: " + customerId;
    }

    @Override
    public String activateCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + customerId
                ));

        customer.setIsActive(true);
        customerRepository.save(customer);
        return "Customer activated successfully with ID: " + customerId;
    }

    @Override
    public String deactivateCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with ID: " + customerId
                ));

        customer.setIsActive(false);
        customerRepository.save(customer);
        return "Customer deactivated successfully with ID: " + customerId;
    }
}