package com.perfulandia.vendedores.controller;


import com.perfulandia.vendedores.dto.VendedorDTO;
import com.perfulandia.vendedores.model.Vendedor;
import com.perfulandia.vendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")


public class VendedorController {
     @Autowired
    private VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<Vendedor> crearVendedor(@RequestBody Vendedor vendedor) {
        Vendedor nuevo = vendedorService.guardarVendedor(vendedor);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable Integer id) {
        return vendedorService.obtenerVendedorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<VendedorDTO> listarTodosLosVendedores() {
        return vendedorService.obtenerTodosLosVendedoresDTO();
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
    public ResponseEntity<List<Vendedor>> obtenerVendedoresPorSucursal(@PathVariable String sucursal) {
        List<Vendedor> vendedores = vendedorService.obtenerVendedoresPorSucursal(sucursal);
        if (vendedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vendedores);
    }
   
    

}
