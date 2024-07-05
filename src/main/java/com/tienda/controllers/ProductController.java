package com.tienda.controllers;

import com.tienda.domain.dtos.ProductoDto;
import com.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/producto")
public class ProductController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getProductos(){
        return ResponseEntity.ok(this.productoService.getAllProducts());
    }

    @PostMapping
    public ProductoDto createProduct(@RequestBody ProductoDto product) {
        return productoService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ProductoDto updateProduct(@PathVariable Long id, @RequestBody ProductoDto productDetails) {
        return productoService.updateProduct(id, productDetails);
    }

    @PostMapping("/{id}/addStock")
    public ProductoDto addStock(@PathVariable Long id, @RequestParam int additionalStock) {
        return productoService.addStock(id, additionalStock);
    }

    @GetMapping("/available")
    public List<ProductoDto> getAvailableProducts() {
        return productoService.getAvailableProducts();
    }

    @PostMapping("/purchase")
    public void purchaseProducts(@RequestBody List<Long> productIds) {
        productoService.purchaseProducts(productIds);
    }
}
