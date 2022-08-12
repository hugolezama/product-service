package com.hlezama.productservice.mapper;

import com.hlezama.productservice.dto.ProductResource;
import com.hlezama.productservice.model.Product;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

  public static ProductResource toDto(Product product) {

    return (product == null) ? null : ProductResource.builder()
        .id(product.getId())
        .description(product.getDescription())
        .name(product.getName())
        .price(product.getPrice())
        .build();
  }

  public static List<ProductResource> toDto(List<Product> products) {

    return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
  }

  public static List<Product> toEntity(List<ProductResource> productResources) {

    return productResources.stream().map(ProductMapper::toEntity).collect(Collectors.toList());
  }

  public static Product toEntity(ProductResource productResource) {

    return (productResource == null) ? null : Product.builder()
        .id(productResource.getId())
        .description(productResource.getDescription())
        .name(productResource.getName())
        .price(productResource.getPrice())
        .build();
  }
}
