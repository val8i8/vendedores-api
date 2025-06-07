package com.perfulandia.vendedores.controller;

import com.perfulandia.vendedores.model.Vendedor;
import com.perfulandia.vendedores.service.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")

public class VendedorController {
    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public Vendedor crearVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.crearVendedor(vendedor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerVendedor(@PathVariable Integer id) {
        return vendedorService.obtenerVendedorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> actualizarVendedor(@PathVariable Integer id, @RequestBody Vendedor vendedor) {
        try {
            Vendedor actualizado = vendedorService.actualizarVendedor(id, vendedor);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sucursal/{sucursal}")
    public List<Vendedor> obtenerPorSucursal(@PathVariable String sucursal) {
        return vendedorService.obtenerPorSucursal(sucursal);
    }

}
