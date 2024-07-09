package com.mangoket.camunda.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    String requester;
    String productId;
    Double newSalePrice;
    Double oldSalePrice;
    long processId;
}
