package com.quadcore.tpts.tptsServiceImpl;

import com.quadcore.tpts.tptsExceptions.CategoryDeletionException;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.ProductCategoryDto;
import com.quadcore.tpts.tptsModelDTO.ProductCategoryResponse;
import com.quadcore.tpts.tptsModels.ProductCategory;
import com.quadcore.tpts.tptsRepositories.ProductCategoryRepository;
import com.quadcore.tpts.tptsService.ProductCategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class ProductCategoryServiceimpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository cateogoryRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public String createCategory( ProductCategory category)
    {
        cateogoryRepo.save(category);
        return "Cateogry Successfully Created !!";
    }

    @Override
    public ProductCategory fetchById(Long categoryId)
    {
        ProductCategory response = cateogoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found!!"));

        return response;
    }

    @Override
    public ProductCategoryResponse fetchByName(String categoryName)
    {
        List<ProductCategory> categories = cateogoryRepo.findByCategoryName(categoryName);
        List<ProductCategoryDto>categoryDtos = categories.stream().map(item->mapper.map(item,ProductCategoryDto.class)).toList();
        ProductCategoryResponse response = new ProductCategoryResponse();
        response.setContent(categoryDtos);
        return response;
    }

    @Override
    public  ProductCategoryResponse fetchAll()
    {
        List<ProductCategory>categories = cateogoryRepo.findAll();
        List<ProductCategoryDto>dtos = categories.stream().map(category->mapper.map(category,ProductCategoryDto.class)).toList();
        ProductCategoryResponse respone = new ProductCategoryResponse();
        respone.setContent(dtos);
        return respone;
    }

    @Override
    public String deletecategory(Long categoryId)
    {
        ProductCategory category = cateogoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException());
        if(!(category.getProduct().isEmpty()))
        {
            int numberOfProducts =category.getProduct().size();

            throw new CategoryDeletionException(categoryId,numberOfProducts);

        }
        cateogoryRepo.deleteById(categoryId);
        return "Successfully Deleted";
    }

}
