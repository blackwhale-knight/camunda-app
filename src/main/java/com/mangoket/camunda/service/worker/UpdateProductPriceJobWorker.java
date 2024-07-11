package com.mangoket.camunda.service.worker;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductPriceJobWorker implements BaseJobWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateProductPriceJobWorker.class);

    private static final String PRODUCT_ID_FIELD = "productId";
    private static final String NEW_SALE_PRICE_FIELD = "newSalePrice";
    @Override
    @JobWorker(type = "updateProductPrice")
    public void handle(JobClient client, ActivatedJob job) {
        String productId = (String) job.getVariablesAsMap().get(PRODUCT_ID_FIELD);
        Double newSalePrice = (Double) job.getVariablesAsMap().get(NEW_SALE_PRICE_FIELD);

        LOGGER.info("Product: {} update to ${}.", productId, newSalePrice);
        /* TODO: Update Product Price
        *   1. by delegate to Mango5 -> throw UpdateProductSalePriceTO to MQ
        *   2. by directly update DB -> hibernate.updateProductSalePrice(productId, newSalePrice);
        * */
        client.newCompleteCommand(job.getKey()).send().join();
    }


}
