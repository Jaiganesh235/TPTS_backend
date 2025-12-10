package com.quadcore.tpts.tptsModelDTO.ComponentDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDto {

    private Long categoryId;

    @NotBlank(message = "Category name cannot be empty.")
    private String categoryName;

    @NotBlank(message = "Category code is required.")
    private String categoryCode;

    @Column(name = "category_description")
    private String categoryDescription;
}
