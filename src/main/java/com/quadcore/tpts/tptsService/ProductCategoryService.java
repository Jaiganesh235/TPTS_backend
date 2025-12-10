package com.quadcore.tpts.tptsService;

import com.quadcore.tpts.tptsModelDTO.ProductCategoryResponse;
import com.quadcore.tpts.tptsModels.ProductCategory;
import jakarta.validation.Valid;

public interface ProductCategoryService {
    String createCategory(@Valid ProductCategory category);
    ProductCategory fetchById(Long categoryId);

    ProductCategoryResponse fetchByName(String categoryName);

    ProductCategoryResponse fetchAll();
    String deletecategory(Long categoryId);
}
