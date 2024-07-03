package com.chayan.rest.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chayan.rest.entity.Order;
import com.chayan.rest.service.OrderService;


@RestController
@RequestMapping("/api/v1/order-service")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping(value = "/orders/{id}", headers = "content-type=application/json",
      consumes = "application/json", produces = "application/json")
  public ResponseEntity<Order> getOrder(@PathVariable Long id) {

    return orderService.getOrder(id).map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());

  }

  @PostMapping(value = "/order", headers = "content-type=application/json",
      consumes = "application/json", produces = "application/json")
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {

    return Optional.ofNullable(orderService.createOrder(order)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());

  }

  @PatchMapping(value = "/orders/{id}", headers = "content-type=application/json",
      consumes = "application/json", produces = "application/json")
  public ResponseEntity<Order> updatePartialOrder(@RequestBody Order order, @PathVariable Long id) {

    return Optional.ofNullable(orderService.updatePartialOrder(order, id)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());

  }

  @PutMapping(value = "/orders/{id}", headers = "content-type=application/json",
      consumes = "application/json", produces = "application/json")
  public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id) {

    return Optional.ofNullable(orderService.updateOrder(order, id)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());

  }

  @DeleteMapping(value = "/orders/{id}", headers = "content-type=application/json",
      consumes = "application/json", produces = "application/json")

  public ResponseEntity<Long> deleteOrder(@PathVariable Long id) {

    return Optional.ofNullable(orderService.deleteOrder(id)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());

  }

}
