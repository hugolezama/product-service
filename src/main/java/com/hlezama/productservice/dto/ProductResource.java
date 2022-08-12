package com.hlezama.productservice.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResource {

  private String id;
  private String name;
  private String description;
  private BigDecimal price;

}
