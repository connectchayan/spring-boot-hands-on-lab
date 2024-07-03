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

  public Order updatePartialOrder(Order order, Long id) {
    return orderRepository.findById(id).map(ordr -> {
      if (StringUtils.isNotBlank(order.getDescription()))
        ordr.setDescription(order.getDescription());

      if (Objects.nonNull(order.getDelivery())) {
        populateDelivery(order, ordr);
      }

      if (Objects.nonNull(order.getItem())) {
        List<Item> tempList = new ArrayList<>();
        order.getItem().forEach(itm -> {
          if (StringUtils.isNotBlank(itm.getName()))
            tempList.add(itm);
        });
        ordr.getItem().addAll(tempList);
      }
      return orderRepository.save(ordr);
    }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
  }

  private void populateDelivery(Order order, Order ordr) {
    if (StringUtils.isNotBlank(order.getDelivery().getDeliveryAddress()))
      ordr.getDelivery().setDeliveryAddress(order.getDelivery().getDeliveryAddress());
    if (Objects.nonNull(order.getDelivery().getDeliveryMethod()))
      ordr.getDelivery().setDeliveryMethod(order.getDelivery().getDeliveryMethod());
    if (StringUtils.isNotBlank(order.getDelivery().getEstimatedDeliveryDate()))
      ordr.getDelivery().setEstimatedDeliveryDate(order.getDelivery().getEstimatedDeliveryDate());
  }

  public Order updateOrder(Order order, Long id) {

    Optional<Order> existingOrder = orderRepository.findById(id);
    Order ordr = null;
    if (existingOrder.isPresent()) {

      ordr = existingOrder.get();
      ordr.setDelivery(order.getDelivery());
      ordr.setItem(order.getItem());
      ordr.setDescription(order.getDescription());

      return orderRepository.save(ordr);
    } else {
      throw new ResourceNotFoundException("Updation failed for order_id: " + id);
    }
  }

  public Long deleteOrder(Long id) {

    Optional<Order> existingOrder = orderRepository.findById(id);

    if (existingOrder.isPresent()) {

      orderRepository.deleteById(id);

      return id;
    } else {

      throw new ResourceNotFoundException("Unable to delete with id " + id);
    }

  }


}
