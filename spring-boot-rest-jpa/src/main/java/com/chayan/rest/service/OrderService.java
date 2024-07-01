package com.chayan.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chayan.rest.dao.OrderRepository;
import com.chayan.rest.entity.Order;
import com.chayan.rest.exception.ResourceNotFoundException;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;


  public Optional<Order> getOrder(Long id) {

    return Optional.ofNullable(orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id)));

  }

  public Order createOrder(Order order) {

    return orderRepository.save(order);

  }

  public Order updateOrder(Order order, Long id) {

    Optional<Order> existingOrder = orderRepository.findById(id);

    if (existingOrder.isPresent()) {
      Order ordr = existingOrder.get();

      ordr.setDescription(order.getDescription());

      ordr.setItem(order.getItem());

      return orderRepository.save(ordr);
    } else {
      throw new ResourceNotFoundException("Order not found with id: " + id);
    }

  }

}
