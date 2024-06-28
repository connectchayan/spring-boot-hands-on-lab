package com.chayan.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chayan.rest.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	

}
