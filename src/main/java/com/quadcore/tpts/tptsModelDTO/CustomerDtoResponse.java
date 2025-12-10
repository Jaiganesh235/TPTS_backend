package com.quadcore.tpts.tptsModelDTO;

import com.quadcore.tpts.tptsModelDTO.ComponentDto.customerDto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoResponse {

    private List<CustomerDto> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean last;
}
