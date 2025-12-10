package com.quadcore.tpts.tptsServiceImpl;

import com.quadcore.tpts.tptsModelDTO.CompanyProductUserSideDtoResponse;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductUserSideDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductsDTO;
import com.quadcore.tpts.tptsModelDTO.CompanyProductsDtoResponse;
import com.quadcore.tpts.tptsModels.Company;
import com.quadcore.tpts.tptsModels.CompanyProducts;
import com.quadcore.tpts.tptsModels.ProductCategory;
import com.quadcore.tpts.tptsRepositories.CompanyProductsRepository;
import com.quadcore.tpts.tptsRepositories.CompanyRepository;
import com.quadcore.tpts.tptsRepositories.ProductCategoryRepository;
import com.quadcore.tpts.tptsService.CompanyProductServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyProductServiceImpl implements CompanyProductServices {
    @Autowired
    private CompanyProductsRepository companyproductrepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CompanyRepository companyrepo;

    @Autowired
    private ProductCategoryRepository categoryrepo;


    @Override
    public String createProduct( CompanyProductsDTO product)
    {
        Company company = companyrepo.findById(product.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        ProductCategory category = categoryrepo.findById(product.getCategoryId()).orElseThrow(()->new RuntimeException("Not found"));

        CompanyProducts companyproduct = new CompanyProducts();

        companyproduct.setProductName(product.getProductName());
        companyproduct.setProductDescription(product.getProductDescription());
        companyproduct.setCompanyItBelongs(company);
        companyproduct.setCategory(category);
        companyproduct.setMinWeight(product.getMinWeight());
        companyproduct.setMaxWeight(product.getMaxWeight());
       companyproduct.setMaxDistanceLimit(product.getMaxDistanceLimit());
        companyproduct.setBasePrice(product.getBasePrice());
        companyproduct.setDiscountPercentage(product.getDiscountPercentage());
        companyproduct.setFinalPrice(product.getFinalPrice());

        companyproductrepo.save(companyproduct);
        return "Product has been Successfully Created !! ";
    }

    @Override
    public CompanyProductsDtoResponse fetchCompanyProducts(Long companyId) {

       List<CompanyProducts> products = companyproductrepo.findByCompanyItBelongs_CompanyPlatformId(companyId);
       List<CompanyProductsDTO>listofProducts = products.stream().map(product->mapper.map(product,CompanyProductsDTO.class)).toList();
        CompanyProductsDtoResponse response = new CompanyProductsDtoResponse();
        response.setContent(listofProducts);
        return response;
    }

    @Override
    public CompanyProductUserSideDtoResponse fetchallproducts()
    {
        CompanyProductUserSideDtoResponse response = new CompanyProductUserSideDtoResponse();
        List<CompanyProductUserSideDto>usersideList = new ArrayList<>();
        List<CompanyProducts>products = companyproductrepo.findAll();
        for(CompanyProducts iter : products)
        {
            String companyName = iter.getCompanyItBelongs().getCompanyName();
            String categoryName = iter.getCategory().getCategoryName();
            CompanyProductUserSideDto object = new CompanyProductUserSideDto();
            object.setCompanyName(companyName);
            object.setCategoryType(categoryName);
            object.setProductName(iter.getProductName());
            object.setBasePrice(iter.getBasePrice());
            object.setProductDescription(iter.getProductDescription());
            object.setDiscountPercentage(iter.getDiscountPercentage());
            object.setFinalPrice(iter.getFinalPrice());
            object.setMinWeight(iter.getMinWeight());
            object.setMaxWeight(iter.getMaxWeight());
            object.setMaxDistanceLimit(iter.getMaxDistanceLimit());
            usersideList.add(object);
        }
        response.setContent(usersideList);
        return response;
    }

    @Override
    public String deleteProduct(Long productId)
    {
        companyproductrepo.deleteById(productId);
        return "Product Successfully Deleted!!";
    }

    @Override
    public String updatePrdouct(Long proudctId,CompanyProductsDTO updatedProduct)
    {
        CompanyProducts existingproduct = companyproductrepo.findById(proudctId).get();
        existingproduct.setProductName(updatedProduct.getProductName());
        existingproduct.setProductDescription(updatedProduct.getProductDescription());
        existingproduct.setBasePrice(updatedProduct.getBasePrice());
        existingproduct.setMinWeight(updatedProduct.getMinWeight());
        existingproduct.setMaxWeight(updatedProduct.getMaxWeight());
        existingproduct.setDiscountPercentage(updatedProduct.getDiscountPercentage());

        companyproductrepo.save(existingproduct);
        return "Successfully updated!!";

    }

}
