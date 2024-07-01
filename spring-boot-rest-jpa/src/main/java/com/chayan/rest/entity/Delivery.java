package com.chayan.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

  private Long deliveryID;
  private Long orderID;
  private String deliveryAddress;
  private Enum<DeliveryMethod> deliveryMethod;
  private String estimatedDeliveryDate;


}
