package com.mangoket.camunda.controller.helper;

import com.mangoket.camunda.controller.request.process.UpdateProductPriceProcessRequest;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProcessVariablesAssemblerTest {
    private static final String REQUESTER_FIELD = "requester";
    private static final String PRODUCT_ID_FIELD = "productId";
    private static final String NEW_SALE_PRICE_FIELD = "newSalePrice";
    private static final String OLD_SALE_PRICE_FIELD = "oldSalePrice";

    private static final String TEST_REQUESTER = "test_requester";
    private static final String TEST_PRODUCT_ID = "test_product_id";
    private static final double TEST_NEW_SALE_PRICE = 11.11;
    private static final double TEST_OLD_SALE_PRICE = 22.22;

    @Test
    void testAssembleUpdateProductPriceProcessVariables() {
        UpdateProductPriceProcessRequest request = new UpdateProductPriceProcessRequest();
        request.setRequester(TEST_REQUESTER);
        request.setProductId(TEST_PRODUCT_ID);
        request.setNewSalePrice(TEST_NEW_SALE_PRICE);
        request.setOldSalePrice(TEST_OLD_SALE_PRICE);

        Map<String, Object> ret = ProcessVariablesAssembler.assembleUpdateProductPriceProcessVariables(request);

        assertEquals(TEST_REQUESTER, ret.get(REQUESTER_FIELD));
        assertEquals(TEST_PRODUCT_ID, ret.get(PRODUCT_ID_FIELD));
        assertEquals(TEST_NEW_SALE_PRICE, ret.get(NEW_SALE_PRICE_FIELD));
        assertEquals(TEST_OLD_SALE_PRICE, ret.get(OLD_SALE_PRICE_FIELD));
    }
}