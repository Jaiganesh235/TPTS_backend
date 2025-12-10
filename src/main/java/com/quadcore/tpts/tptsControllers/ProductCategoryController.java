package com.quadcore.tpts.tptsControllers;

import com.quadcore.tpts.tptsModelDTO.ProductCategoryResponse;
import com.quadcore.tpts.tptsModels.ProductCategory;
import com.quadcore.tpts.tptsService.ProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tpts/superAdmin/categories")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<String> createCatgory(@Valid @RequestBody ProductCategory category)
    {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/byId/{categoryId}")
    public ProductCategory fetchbyId(@PathVariable Long categoryId)
    {
        return categoryService.fetchById(categoryId);
    }


    @GetMapping()
    public ResponseEntity<ProductCategoryResponse>fetchByname(@RequestParam(name = "categoryName")String categoryName)
    {
        return new ResponseEntity<>(categoryService.fetchByName(categoryName),HttpStatus.FOUND);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<ProductCategoryResponse>fetchAll()
    {
        return new ResponseEntity<>(categoryService.fetchAll(),HttpStatus.OK);
    }

    @DeleteMapping("/deletecategory/{categoryId}")
    public ResponseEntity<String>deleteCategory(@PathVariable Long categoryId)
    {
        return new ResponseEntity<>(categoryService.deletecategory(categoryId),HttpStatus.OK);
    }

}
