package com.app.inventorymanagerapp.service;

import com.app.inventorymanagerapp.dao.ProductRepository;
import com.app.inventorymanagerapp.dto.ProductDto;
import com.app.inventorymanagerapp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDtoService {

    private ProductRepository productRepository;

    @Autowired
    public ProductDtoService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public List<ProductDto> findAll(){
        return ((List<Product>) productRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    private ProductDto convertDataIntoDTO(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getProductId());
        dto.setBrand(product.getBrand());
        dto.setProductName(product.getProductName());
        dto.setExpiryDate(product.getExpiryDate());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        return dto;
    }


}
