package com.perfulandia.vendedores.service;

import com.perfulandia.vendedores.dto.VendedorDTO;
import com.perfulandia.vendedores.model.Vendedor;
import com.perfulandia.vendedores.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    // Guardar vendedor
    public Vendedor guardarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Obtener vendedor por ID (entidad)
    public Optional<Vendedor> obtenerVendedorPorId(Integer idVendedor) {
        return vendedorRepository.findById(idVendedor);
    }

    // Obtener vendedor por ID en formato DTO
    public Optional<VendedorDTO> obtenerVendedorDTOporId(Integer idVendedor) {
        return vendedorRepository.findById(idVendedor)
                .map(v -> new VendedorDTO(
                        v.getIdVendedor(),
                        v.getUsuarioId(),
                        v.getSucursal(),
                        v.getMetaMensual(),
                        v.getNombre(),
                        v.getRut(),
                        v.getAreaVentas()
                ));
    }

    // Listar todos los vendedores en formato DTO
    public List<VendedorDTO> obtenerTodosLosVendedoresDTO() {
        return vendedorRepository.findAll()
                .stream()
                .map(v -> new VendedorDTO(
                        v.getIdVendedor(),
                        v.getUsuarioId(),
                        v.getSucursal(),
                        v.getMetaMensual(),
                        v.getNombre(),
                        v.getRut(),
                        v.getAreaVentas()
                ))
                .collect(Collectors.toList());
    }

    // Listar vendedores por sucursal
    public List<Vendedor> obtenerVendedoresPorSucursal(String sucursal) {
        return vendedorRepository.findBySucursal(sucursal);
    }

    // Actualizar vendedor
    public Vendedor actualizarVendedor(Integer idVendedor, Vendedor vendedor) {
        Vendedor actual = vendedorRepository.findById(idVendedor)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        actual.setUsuarioId(vendedor.getUsuarioId());
        actual.setSucursal(vendedor.getSucursal());
        actual.setMetaMensual(vendedor.getMetaMensual());
        actual.setNombre(vendedor.getNombre());
        actual.setRut(vendedor.getRut());
        actual.setAreaVentas(vendedor.getAreaVentas());

        return vendedorRepository.save(actual);
    }


}
