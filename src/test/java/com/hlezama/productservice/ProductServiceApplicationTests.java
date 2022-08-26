package com.hlezama.productservice;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlezama.productservice.dto.ProductResource;
import com.hlezama.productservice.mapper.ProductMapper;
import com.hlezama.productservice.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

  @Container
  static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0");

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProductRepository productRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  void shouldCreateProduct() throws Exception {
    mockMvc
        .perform(
            post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getSampleProduct())))
        .andExpect(status().isCreated());
    Assertions.assertEquals(1, productRepository.findAll().size());
  }

  @Test
  void shouldGetProductList() throws Exception {
    productRepository.save(ProductMapper.toEntity(getSampleProduct()));
    productRepository.save(ProductMapper.toEntity(getSampleProduct()));

    mockMvc
        .perform(get("/api/product"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()", is(2)));
  }

  private ProductResource getSampleProduct() {
    Random r = new Random();
    return ProductResource.builder()
        .name("Apple iPhone " + r.nextInt(15))
        .description("iPhone 64Gb Black")
        .price(BigDecimal.valueOf(r.nextInt(2500)))
        .build();
  }
}
