package com.quadcore.tpts.tptsService;

import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyDto;
import com.quadcore.tpts.tptsModelDTO.CompanyDtoResponse;
import com.quadcore.tpts.tptsModels.Company;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CompanyServices {
    String createCompany(Company com);
    CompanyDtoResponse fetchAllCompanies(Integer pageNo, Integer pageSize);
    CompanyDto getByName(String name);
    String removeCompany(Long companyId);
    String updateCompany(Company newData, Long id);

    CompanyDtoResponse getByName(String name,Integer pageNo, Integer pageSize);

    CompanyDtoResponse fetchByServiceArea(String location);
}
