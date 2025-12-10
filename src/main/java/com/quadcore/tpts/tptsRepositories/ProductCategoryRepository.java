package com.quadcore.tpts.tptsRepositories;

import com.quadcore.tpts.tptsControllers.ProductCategoryController;
import com.quadcore.tpts.tptsModels.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    List<ProductCategory> findByCategoryName(String name);
}
