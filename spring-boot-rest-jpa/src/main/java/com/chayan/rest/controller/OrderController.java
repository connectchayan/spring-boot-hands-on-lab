package com.chayan.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable Long id) {

		return orderService.getOrder(id)
				.map(ordr -> ResponseEntity.ok(ordr))
				.orElse(ResponseEntity.noContent().build());

	}
	
	@PostMapping("/order")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {

		return Optional.ofNullable(orderService.createOrder(order))
				.map(ordr -> ResponseEntity.ok(ordr))
				.orElse(ResponseEntity.badRequest().build());

	}
	
	@PatchMapping("/orders/{id}")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable Long id) throws Exception {

		return Optional.ofNullable(orderService.updateOrder(order,id))
				.map(ordr -> ResponseEntity.ok(ordr))
				.orElse(ResponseEntity.badRequest().build());

	}
	

}
