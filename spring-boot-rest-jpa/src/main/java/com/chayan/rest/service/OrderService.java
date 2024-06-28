package com.chayan.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.chayan.rest.dao.OrderRepository;
import com.chayan.rest.entity.Item;
import com.chayan.rest.entity.Order;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Optional<Order> getOrder(Long id) {

		return orderRepository.findById(id);

	}

	public Order createOrder(Order order) {

		return orderRepository.save(order);

	}

	public Order updateOrder(Order order, Long id) {

		Optional<Order> existingOrder = orderRepository.findById(id);

		if (existingOrder.isPresent()) {
			Order ordr = existingOrder.get();

			ordr.setDescription(order.getDescription());
			/*
			 * List<Item> updatedItems = new ArrayList<>(); for (Item item :
			 * order.getItem()) { item.setOrder(existingOrder.get());
			 * updatedItems.add(item); } existingOrder.get().setItem(updatedItems);
			 */
		        ordr.setItem(order.getItem());

			return orderRepository.save(ordr);
		} else {
			throw new EmptyResultDataAccessException(("Order not found with id: " + id), 1);
		}

	}

}
