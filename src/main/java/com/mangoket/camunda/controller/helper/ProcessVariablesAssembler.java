package com.mangoket.camunda.controller.helper;


import com.mangoket.camunda.controller.request.UpdateProductPriceProcessRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProcessVariablesAssembler {

    private static final String REQUESTER_FIELD = "requester";
    private static final String PRODUCT_ID_FIELD = "productId";
    private static final String NEW_SALE_PRICE_FIELD = "newSalePrice";
    private static final String OLD_SALE_PRICE_FIELD = "oldSalePrice";

    public Map<String, Object> assembleUpdateProductPriceProcessVariables(UpdateProductPriceProcessRequest request) {
        Map<String, Object> variables = new HashMap<>();
        variables.put(REQUESTER_FIELD, request.getRequester());
        variables.put(PRODUCT_ID_FIELD, request.getProductId());
        variables.put(NEW_SALE_PRICE_FIELD, request.getNewSalePrice());
        variables.put(OLD_SALE_PRICE_FIELD, request.getOldSalePrice());
        return variables;
    }
}
