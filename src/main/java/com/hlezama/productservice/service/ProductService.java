package com.hlezama.productservice.service;

import com.hlezama.productservice.dto.ProductResource;
import com.hlezama.productservice.mapper.ProductMapper;
import com.hlezama.productservice.model.Product;
import com.hlezama.productservice.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResource createProduct(ProductResource productResource) {

    log.info("Creating product: {}", productResource);
    Product saved = productRepository.save(ProductMapper.toEntity(productResource));
    log.info("Product with ID: {} created", saved.getId());
    return ProductMapper.toDto(saved);
  }

  public List<ProductResource> listAll() {
    return ProductMapper.toDto(productRepository.findAll());
  }
}
