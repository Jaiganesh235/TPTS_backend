package com.quadcore.tpts.tptsControllers;

import com.quadcore.tpts.tptsConfig.AppConst;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerLoginDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerRegistrationDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerUpdateDto;
import com.quadcore.tpts.tptsModelDTO.CustomerDtoResponse;
import com.quadcore.tpts.tptsServiceImpl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tpts/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    // Register new customer
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(
            @Valid @RequestBody CustomerRegistrationDto registrationDto) {
        String output = customerService.registerCustomer(registrationDto);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    // Login customer
    @PostMapping("/login")
    public ResponseEntity<CustomerDto> loginCustomer(
            @Valid @RequestBody CustomerLoginDto loginDto) {
        CustomerDto customer = customerService.loginCustomer(loginDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Fetch all customers with pagination
    @GetMapping("/fetchCustomers")
    public ResponseEntity<CustomerDtoResponse> fetchAllCustomers(
            @RequestParam(name = "pageNo", defaultValue = AppConst.PAGE_NUMBER) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConst.PAGE_SIZE) Integer pageSize) {
        return new ResponseEntity<>(customerService.fetchAllCustomers(pageNo, pageSize), HttpStatus.OK);
    }

    // Get customer by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Get customer by email
    @GetMapping("/email")
    public ResponseEntity<CustomerDto> getCustomerByEmail(@RequestParam String email) {
        CustomerDto customer = customerService.getCustomerByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Search customers by name with pagination
    @GetMapping("/searchByName")
    public ResponseEntity<CustomerDtoResponse> searchCustomersByName(
            @RequestParam String name,
            @RequestParam(name = "pageNo", defaultValue = AppConst.PAGE_NUMBER) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConst.PAGE_SIZE) Integer pageSize) {
        return new ResponseEntity<>(customerService.searchCustomersByName(name, pageNo, pageSize), HttpStatus.OK);
    }

    // Filter customers by city with pagination
    @GetMapping("/filterByCity")
    public ResponseEntity<CustomerDtoResponse> filterCustomersByCity(
            @RequestParam String city,
            @RequestParam(name = "pageNo", defaultValue = AppConst.PAGE_NUMBER) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = AppConst.PAGE_SIZE) Integer pageSize) {
        return new ResponseEntity<>(customerService.filterCustomersByCity(city, pageNo, pageSize), HttpStatus.OK);
    }

    // Update customer
    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<String> updateCustomer(
            @Valid @RequestBody CustomerUpdateDto updateDto,
            @PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.updateCustomer(updateDto, customerId), HttpStatus.OK);
    }

    // Delete customer (soft delete)
    @DeleteMapping("/removeCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

    // Activate customer
    @PatchMapping("/activate/{customerId}")
    public ResponseEntity<String> activateCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.activateCustomer(customerId), HttpStatus.OK);
    }

    // Deactivate customer
    @PatchMapping("/deactivate/{customerId}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(customerService.deactivateCustomer(customerId), HttpStatus.OK);
    }
}