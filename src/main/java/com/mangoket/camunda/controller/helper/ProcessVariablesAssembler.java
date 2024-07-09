package com.mangoket.camunda.controller.helper;


import com.mangoket.camunda.controller.request.process.UpdateProductPriceProcessRequest;

import java.util.HashMap;
import java.util.Map;

public class ProcessVariablesAssembler {
    public static Map<String, Object> assembleUpdateProductPriceProcessVariables(UpdateProductPriceProcessRequest request) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("requester", request.getRequester());
        variables.put("productId", request.getProductId());
        variables.put("newSalePrice", request.getNewSalePrice());
        variables.put("oldSalePrice", request.getOldSalePrice());
        return variables;
    }
}
