package com.mangoket.camunda.controller.request.process;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductPriceProcessRequest extends ProcessRequest {
    @NonNull
    private String productId;
    private double newSalePrice;
    private double oldSalePrice;
}
