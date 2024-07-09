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
    @Override
    @JobWorker(type = "updateProductPrice", fetchVariables={"productId", "newSalePrice"})
    public void handle(JobClient client, ActivatedJob job) {
        String productId = (String)job.getVariablesAsMap().get("productId");
        Double newSalePrice = (Double)job.getVariablesAsMap().get("newSalePrice");
        LOGGER.info("Product: {} update to ${}.", productId, newSalePrice);
        /* TODO: Update Product Price
        *   1. by delegate to Mango5 -> throw UpdateProductSalePriceTO to MQ
        *   2. by directly update DB -> hibernate.updateProductSalePrice(productId, newSalePrice);
        * */
        client.newCompleteCommand(job.getKey()).send().join();
    }


}
