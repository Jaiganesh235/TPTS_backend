package com.quadcore.tpts.tptsControllers;

import com.quadcore.tpts.tptsConfig.AppConst;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyDto;
import com.quadcore.tpts.tptsModelDTO.CompanyDtoResponse;
import com.quadcore.tpts.tptsModels.Company;
import com.quadcore.tpts.tptsServiceImpl.CompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tpts/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyservice;

    @PostMapping("/createcompany")
    public ResponseEntity<String>createCompany(@Valid @RequestBody Company company)
    {
        String output = companyservice.createCompany(company);
        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    //FetchCompanies with Pagination
    @GetMapping("/fetchCompanies")
     public ResponseEntity<CompanyDtoResponse>fetchAllCompanies(
            @RequestParam(name="pageNo",defaultValue = AppConst.PAGE_NUMBER)Integer pageNo,
            @RequestParam(name = "pageSize",defaultValue = AppConst.PAGE_SIZE)Integer pageSize)
     {
         return new ResponseEntity<>(companyservice.fetchAllCompanies(pageNo,pageSize),HttpStatus.OK);
     }

     @GetMapping("/filterbyname")
     public CompanyDto fetchByName(@RequestParam String name)
     {
         return companyservice.getByName(name);
     }

     @DeleteMapping("/removeCompany/{companyId}")
     public ResponseEntity<String> deleteCompany(@PathVariable Long companyId)
     {
         return new ResponseEntity<>(companyservice.removeCompany(companyId),HttpStatus.OK);
     }

     @PutMapping("/updateCompany/{id}")
     public ResponseEntity<String>updateCompany(@Valid @RequestBody Company company,@PathVariable Long id)
     {
         return new ResponseEntity<>(companyservice.updateCompany(company,id),HttpStatus.OK);
     }



     // Fetch Company by name with Pagination
    @GetMapping("/filterbyname/page")
    public ResponseEntity<CompanyDtoResponse> fetchByName(@RequestParam String name,
                                  @RequestParam(name="pageNo",defaultValue = AppConst.PAGE_NUMBER)Integer pageNo,
                                  @RequestParam(name = "pageSize",defaultValue = AppConst.PAGE_SIZE)Integer pageSize)
    {
        return new ResponseEntity<>(companyservice.getByName(name,pageNo,pageSize),HttpStatus.FOUND);
    }

    @GetMapping("/fetchByServiceAreas")
    public ResponseEntity<CompanyDtoResponse>fetchByServiceAreas(@RequestParam(name = "location")String location)
    {
        return new ResponseEntity<>(companyservice.fetchByServiceArea(location),HttpStatus.FOUND);
    }



}
