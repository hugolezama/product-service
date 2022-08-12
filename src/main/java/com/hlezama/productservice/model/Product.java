package com.hlezama.productservice.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "product")
@Builder
@Data
public class Product {

  @Id
  private String id;

  private String name;
  private String description;
  private BigDecimal price;

}
