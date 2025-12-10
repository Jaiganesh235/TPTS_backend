package com.quadcore.tpts.tptsModelDTO;


import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductUserSideDto;
import com.quadcore.tpts.tptsModelDTO.ComponentDto.CompanyProductsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProductUserSideDtoResponse {
    private List<CompanyProductUserSideDto> content;

}
