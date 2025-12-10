package com.quadcore.tpts.tptsServiceImpl;

import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyDto;
import com.quadcore.tpts.tptsModelDTO.CompanyDtoResponse;
import com.quadcore.tpts.tptsModels.Company;
import com.quadcore.tpts.tptsRepositories.CompanyRepository;
import com.quadcore.tpts.tptsService.CompanyServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyServices {

    @Autowired
    private CompanyRepository comrepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String createCompany(Company com)
    {
        comrepo.save(com);
        return "Company Has been successfully Registered";
    }


    @Override
    public CompanyDtoResponse fetchAllCompanies(Integer pageNo, Integer pageSize) {

        Pageable pageDetails = PageRequest.of(pageNo, pageSize);
        Page<Company> page = comrepo.findAll(pageDetails);
        List<CompanyDto>companiesDtos = page.getContent().stream().map(company->mapper.map(company,CompanyDto.class)).toList();
        CompanyDtoResponse response = new CompanyDtoResponse();
        response.setContent(companiesDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;

    }

    @Override
    public CompanyDto getByName(String name) {

        Company searchedCompany = comrepo.findByCompanyName(name);

        CompanyDto response = mapper.map(searchedCompany,CompanyDto.class);

        return response;
    }

    @Override
    public String removeCompany(Long companyId) {

        comrepo.deleteById(companyId);
        return "Company Successfully Deleted!!";
    }

    @Override
    public String updateCompany(Company newData, Long id) {

        Company oldInfo = comrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
        oldInfo.setCompanyName(newData.getCompanyName());
        oldInfo.setCompanyEmail(newData.getCompanyEmail());
        oldInfo.setCompanyPhoneNumber(newData.getCompanyPhoneNumber());
        oldInfo.setCompanyAddress(newData.getCompanyAddress());
        oldInfo.setCity(newData.getCity());
        oldInfo.setPincode(newData.getPincode());
        oldInfo.setServiceAreas(newData.getServiceAreas());
        oldInfo.setCompanyGstNumber(newData.getCompanyGstNumber());
        oldInfo.setCompanyBusinessLicense(newData.getCompanyBusinessLicense());
        oldInfo.setBaseRate(newData.getBaseRate());
        oldInfo.setRatePerKm(newData.getRatePerKm());
        oldInfo.setRatePerKg(newData.getRatePerKg());
        oldInfo.setEcoVehiclePercentage(newData.getEcoVehiclePercentage());
        oldInfo.setAverageRating(newData.getAverageRating());
        oldInfo.setTotalDeliveries(newData.getTotalDeliveries());
        oldInfo.setCommissionRate(newData.getCommissionRate());
        oldInfo.setStatus(newData.getStatus());

        if (newData.getAdmin() != null) {
            oldInfo.getAdmin().setCompanyAdminName(newData.getAdmin().getCompanyAdminName());
            oldInfo.getAdmin().setCompanyAdminPostion(newData.getAdmin().getCompanyAdminPostion());
            oldInfo.getAdmin().setCompanyAdminOfficeIdNumber(newData.getAdmin().getCompanyAdminOfficeIdNumber());
            oldInfo.getAdmin().setCompanyAdminEmail(newData.getAdmin().getCompanyAdminEmail());
            oldInfo.getAdmin().setCompanyAdminPhoneNo(newData.getAdmin().getCompanyAdminPhoneNo());
        }
        comrepo.save(oldInfo);

        return "Company updated successfully updated!!";
    }
    @Override
    public CompanyDtoResponse getByName(String name,Integer pageNo, Integer pageSize) {

        Pageable pageDetails = PageRequest.of(pageNo,pageSize);
        Page<Company>page = comrepo.findByCompanyName(name,pageDetails);

        List<CompanyDto>dtos = page.getContent().stream().map(company->mapper.map(company,CompanyDto.class)).toList();
        CompanyDtoResponse response = new CompanyDtoResponse();
        response.setContent(dtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }

    @Override
    public CompanyDtoResponse fetchByServiceArea(String location)
    {
        List<Company>companesInDb=comrepo.findAll();
        List<CompanyDto>dto = new ArrayList<>();
        for(Company com : companesInDb)
        {
            if(com.getServiceAreas().contains(location))
            {
                CompanyDto requiredCompany = mapper.map(com,CompanyDto.class);
                dto.add(requiredCompany);
            }
        }
        CompanyDtoResponse response = new CompanyDtoResponse();
        response.setContent(dto);
        return response;
    }



}
