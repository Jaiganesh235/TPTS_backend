package com.quadcore.tpts.tptsModelDTO;

import com.quadcore.tpts.tptsModelDTO.ComponentDto.ProductCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryResponse {
    private List<ProductCategoryDto> content;
}
