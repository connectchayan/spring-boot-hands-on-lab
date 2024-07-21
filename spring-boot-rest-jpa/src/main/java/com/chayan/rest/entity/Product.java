package com.chayan.rest.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_DETAILS")
public class Product implements Serializable {/**
   * 
   */
  private static final long serialVersionUID = 4051495450952586762L;

 private Long id;
 
 private List<ProductCategory> productCategory;
  
}
