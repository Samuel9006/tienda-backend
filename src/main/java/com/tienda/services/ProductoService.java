package com.tienda.services;


import com.tienda.domain.dtos.ProductoDto;
import com.tienda.domain.entities.ProductoEntity;
import com.tienda.domain.mappers.ProductoMapper;
import com.tienda.domain.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public List<ProductoDto> getAllProducts() {
        List<ProductoEntity> productos = (List<ProductoEntity>) this.productoRepository.findAll();
        return this.productoMapper.productosToDtos(productos);
    }

    public ProductoDto createProduct(ProductoDto productoDto) {
        ProductoEntity productoEntity = this.productoMapper.productoDtoToProducto(productoDto);
        return this.productoMapper.productToDto(productoRepository.save(productoEntity));
    }

    public ProductoDto updateProduct(Long id, ProductoDto productDetails) {
        return null;
    }

    public ProductoDto addStock(Long id, int additionalStock) {
        return null;
    }

    public List<ProductoDto> getAvailableProducts() {
        return null;
    }

    public void purchaseProducts(List<Long> productIds) {

    }
}

