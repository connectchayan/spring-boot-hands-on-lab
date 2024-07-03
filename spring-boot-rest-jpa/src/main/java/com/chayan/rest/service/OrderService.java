package com.chayan.rest.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import com.chayan.rest.dao.OrderRepository;
import com.chayan.rest.entity.Delivery;
import com.chayan.rest.entity.Item;
import com.chayan.rest.entity.Order;
import com.chayan.rest.exception.ResourceNotFoundException;
import io.micrometer.common.util.StringUtils;

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

  public Order updatePartialOrder(Map<String, Object> propertiesMap, Long id) {

    Optional<Order> existingOrder = orderRepository.findById(id);
    
    if (existingOrder
        .isPresent()) {/*
                        * 
                        * ordr = existingOrder.get();
                        * 
                        * if (StringUtils.isNotBlank(order.getDescription()))
                        * ordr.setDescription(order.getDescription());
                        * 
                        * if (Objects.nonNull(order.getItem())) {
                        * 
                        * order.getItem().forEach(itm -> { if
                        * (StringUtils.isNotBlank(itm.getName())) { existingItems.add(itm); } });
                        * existingOrder.get().getItem().addAll(existingItems); }
                        * 
                        * if (Objects.nonNull(order.getDelivery())) { Delivery existingDelivery =
                        * populateDelivery(order, existingOrder);
                        * ordr.setDelivery(existingDelivery); }
                        */

      propertiesMap.forEach((k, v) -> {
        Field field = ReflectionUtils.findField(Order.class, k);
        field.setAccessible(true);
        ReflectionUtils.setField(field, existingOrder.get(), v);
        
      });

      return orderRepository.save(existingOrder.get());
      
    } else {
      throw new ResourceNotFoundException("Updation failed for order_id: " + id);
    }

  }

  public Order updateOrder(Order order, Long id) {
    
    Optional<Order> existingOrder = orderRepository.findById(id);
    Order ordr = null;
    if (existingOrder.isPresent()) {
      
      ordr=existingOrder.get();
      ordr.setDelivery(order.getDelivery());
      ordr.setItem(order.getItem());
      ordr.setDescription(order.getDescription());
      
      return orderRepository.save(ordr);
    }
    else {
      throw new ResourceNotFoundException("Updation failed for order_id: " + id);
    }
  }

   /* Optional<Order> existingOrder = orderRepository.findById(id);
    Order ordr = null;
    if (existingOrder.isPresent()) {
      ordr = existingOrder.get();
      if (StringUtils.isNotBlank(order.getDescription()))
        ordr.setDescription(order.getDescription());
      if (Objects.nonNull(order.getItem())) {

        List<Item> existingItems = new ArrayList<>();
        order.getItem().forEach(itm -> {
          if (StringUtils.isNotBlank(itm.getName())) {
            existingItems.add(itm);
          }
        });
        existingOrder.get().getItem().addAll(existingItems);
      }

      if (Objects.nonNull(order.getDelivery())) {
        Delivery existingDelivery = populateDelivery(order, existingOrder);
        ordr.setDelivery(existingDelivery);
      }
      return orderRepository.save(ordr);
    } else {
      throw new ResourceNotFoundException("Updation failed for order_id: " + id);
    }

  }*/

}
