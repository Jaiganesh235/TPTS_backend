package com.quadcore.tpts.tptsRepositories;

import com.quadcore.tpts.tptsModels.CompanyProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyProductsRepository extends JpaRepository<CompanyProducts,Long> {

    List<CompanyProducts> findByCompanyItBelongs_CompanyPlatformId(Long companyPlatformId);
}
