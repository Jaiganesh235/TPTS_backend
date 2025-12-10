package com.quadcore.tpts.tptsModelDTO.ComponentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProductsDTO {

    private String productName;

    private String productDescription;

    private Long companyId;

    private Long categoryId;

    private Double minWeight;

    private Double maxWeight;

    private Integer maxDistanceLimit;

    private Double basePrice;

    private Double discountPercentage;

    private Double finalPrice;

}
