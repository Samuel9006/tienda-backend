package com.tienda.services;


import com.tienda.domain.dtos.VentaDto;
import com.tienda.domain.entities.VentaEntity;
import com.tienda.domain.mappers.VentaMapper;
import com.tienda.domain.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaMapper ventaMapper;

    @Transactional
    public VentaDto realizarVenta(VentaDto venta) {
        venta.setFecha(LocalDateTime.now());
        VentaEntity save = ventaRepository.save(this.ventaMapper.toEntity(venta));
        this.productoService.purchaseProducts(venta.getProductos());
        return this.ventaMapper.toDto(save);
    }

    public List<VentaDto> obtenerVentas() {
        return this.ventaMapper.toDtos((List<VentaEntity>) ventaRepository.findAll());
    }
}
