package com.perfulandia.vendedores.service;

import com.perfulandia.vendedores.model.Vendedor;
import com.perfulandia.vendedores.repository.VendedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public Vendedor crearVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Optional<Vendedor> obtenerVendedorPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    public Vendedor actualizarVendedor(Long id, Vendedor vendedorActualizado) {
        Vendedor vendedor = vendedorRepository.findById(id).orElseThrow();
        vendedor.setUsuarioId(vendedorActualizado.getUsuarioId());
        vendedor.setSucursal(vendedorActualizado.getSucursal());
        vendedor.setMetaMensual(vendedorActualizado.getMetaMensual());
        return vendedorRepository.save(vendedor);
    }

    public List<Vendedor> obtenerPorSucursal(String sucursal) {
        return vendedorRepository.findBySucursal(sucursal);
    }

}
