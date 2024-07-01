package com.chayan.rest.entity;

import com.chayan.rest.enums.DeliveryMethod;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_DELIVERY")
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_id")
  private Long deliveryID;
  @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
  private Order order;
  private String deliveryAddress;
  @Enumerated(EnumType.STRING)
  private DeliveryMethod deliveryMethod;
  private String estimatedDeliveryDate;


}
