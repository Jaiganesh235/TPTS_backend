package com.quadcore.tpts.tptsService;

import com.quadcore.tpts.tptsModelDTO.CompanyProductUserSideDtoResponse;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductsDTO;
import com.quadcore.tpts.tptsModelDTO.CompanyProductsDtoResponse;
import org.springframework.http.ResponseEntity;

public interface CompanyProductServices {
    String createProduct( CompanyProductsDTO product);
    CompanyProductsDtoResponse fetchCompanyProducts(Long companyId);
    CompanyProductUserSideDtoResponse fetchallproducts();
    String deleteProduct(Long productId);

    String updatePrdouct(Long proudctId,CompanyProductsDTO updatedProduct);
}
