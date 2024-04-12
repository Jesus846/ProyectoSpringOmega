package com.gestion.empleados.servicio;

import org.springframework.stereotype.Service;

import com.gestion.empleados.models.ProductoModels;

import java.util.Optional;

@Service
public interface ProductoServicies {
    public Iterable<ProductoModels>  findAll(String palabraClave);
    public Optional<ProductoModels> findById(Long id);
    public ProductoModels save(ProductoModels producto);
    public void deleteById(Long id);

}
