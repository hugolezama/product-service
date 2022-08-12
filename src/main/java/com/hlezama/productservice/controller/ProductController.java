package com.hlezama.productservice.controller;

import com.hlezama.productservice.dto.ProductResource;
import com.hlezama.productservice.service.ProductService;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResource createProduct(@RequestBody ProductResource productResource) {
    return productService.createProduct(productResource);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResource> listProducts(){
    return productService.listAll();
  }
}
