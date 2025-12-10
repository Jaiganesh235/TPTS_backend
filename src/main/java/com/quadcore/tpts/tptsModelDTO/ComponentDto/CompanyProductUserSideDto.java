package com.quadcore.tpts.tptsModelDTO.ComponentDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProductUserSideDto {
    private String productName;

    private String productDescription;

    private String companyName;

    private String categoryType;

    private Double minWeight;

    private Double maxWeight;

    private Integer maxDistanceLimit;

    private Double basePrice;

    private Double discountPercentage;

    private Double finalPrice;
}
