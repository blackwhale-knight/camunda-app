package com.mangoket.camunda.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
public class UpdateProductPriceProcessRequest extends ProcessRequest {
    @NotBlank(message = "product id is required")
    private String productId;
    @NotNull(message = "new sale price is required")
    private Double newSalePrice;
    @NotNull(message = "old sale price is required")
    private Double oldSalePrice;
}
