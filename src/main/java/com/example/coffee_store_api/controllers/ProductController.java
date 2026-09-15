package com.example.coffee_store_api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffee_store_api.dto.ProductRequestDto;
import com.example.coffee_store_api.dto.ProductResponseDto;
import com.example.coffee_store_api.services.ProductService;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping()
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto productRequest,
            @AuthenticationPrincipal String userId) {
        return productService.createProduct(productRequest, new ObjectId(userId));
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") String id, @RequestBody ProductRequestDto productRequest) {
        return productService.updateProduct(productRequest, new ObjectId(id));
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/dashboard")
    public Page<ProductResponseDto> getProducts(
            @RequestParam(name = "search", required = false, defaultValue = "") String search, Pageable pageable) {
        return productService.getProducts(pageable, search);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable("id") String id) {
        return productService.getProduct(new ObjectId(id));
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(new ObjectId(id));
    }

}
