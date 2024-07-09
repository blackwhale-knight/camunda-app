package com.mangoket.camunda.controller.request.process;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductPriceProcessRequest extends ProcessRequest {
    private String productId;
    private double newSalePrice;
    private double oldSalePrice;
}
