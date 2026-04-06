package com.comunifield.micromarket.inventory.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SaleRequestDTO {
    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotEmpty(message = "The sale must include at least one product")
    private List<SaleDetailDTO> details;
}
