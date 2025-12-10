package com.quadcore.tpts.tptsControllers;

import com.quadcore.tpts.tptsModelDTO.CompanyProductUserSideDtoResponse;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductUserSideDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductsDTO;
import com.quadcore.tpts.tptsModelDTO.CompanyProductsDtoResponse;
import com.quadcore.tpts.tptsService.CompanyProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/tpts/companyproduct")
public class CompanyProductController {

    @Autowired
    private CompanyProductServices productservices;

    @PostMapping("/createproduct/")
    public ResponseEntity<String> createProduct(@Valid @RequestBody CompanyProductsDTO product)
    {
        String output = productservices.createProduct(product);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }
    @GetMapping("/fetchproducts/{companyId}")
    public ResponseEntity<CompanyProductsDtoResponse> getProducts(@PathVariable Long companyId)
    {
        return new ResponseEntity<>(productservices.fetchCompanyProducts(companyId), HttpStatus.FOUND);
    }
    @GetMapping("/fetchallproducts")
    public ResponseEntity<CompanyProductUserSideDtoResponse>getAllproducts()
    {
        return new ResponseEntity<>(productservices.fetchallproducts(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteproduct/{productId}")
    public ResponseEntity<String>deleteProduct(@PathVariable Long productId){
        return new ResponseEntity<>(productservices.deleteProduct(productId),HttpStatus.OK);

    }

    @PutMapping("/updateProduct/{proudctId}")
    public ResponseEntity<String>updateProudct(@PathVariable Long proudctId,@RequestBody CompanyProductsDTO updatedProduct)
    {
        return new ResponseEntity<>(productservices.updatePrdouct(proudctId,updatedProduct),HttpStatus.OK);
    }

}
