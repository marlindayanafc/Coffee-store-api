package com.example.coffee_store_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.coffee_store_api.dto.ProductResponseDto;
import com.example.coffee_store_api.models.Product;
import com.example.coffee_store_api.repositories.ProductRepository;
import com.example.coffee_store_api.repositories.StatusRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldReturnAndProductWhenExist() {

        ObjectId idP = new ObjectId();
        Product product = new Product("Café Colombia 500g", 25000, 50, new ObjectId(),
                "Café en grano, tueste medio", "Notas a chocolate", new ObjectId(), "imagen.jpg", new ObjectId());
        ReflectionTestUtils.setField(product, "id", idP);

        when(productRepository.findById(idP)).thenReturn(Optional.of(product));

        ProductResponseDto responseDto = productService.getProduct(idP);

        assertEquals("Café Colombia 500g", responseDto.getName());

    }

    @Test
    public void shouldThrowExceptionWhenProductDoesNotExist() {
        ObjectId id = new ObjectId();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> productService.getProduct(id));
    }

}
