package com.chayan.rest.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_DETAILS")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long orderId;
  private String description;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private List<Item> item;

  @OneToOne(mappedBy = "order",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
  private Delivery delivery;

  @JsonManagedReference
  public List<Item> getItem() {
    return item;
  }

  @JsonManagedReference
  public Delivery getDelivery() {
    return delivery;
  }



}
