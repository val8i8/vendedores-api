package com.perfulandia.vendedores.controller;


import com.perfulandia.vendedores.dto.VendedorDTO;
import com.perfulandia.vendedores.model.Vendedor;
import com.perfulandia.vendedores.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

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

    // GET con HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<VendedorDTO>> obtenerVendedorPorId(@PathVariable Integer id) {
        return vendedorService.obtenerVendedorPorId(id)
                .map(vendedor -> {
                    VendedorDTO dto = new VendedorDTO();
                    dto.setIdVendedor(vendedor.getIdVendedor());
                    dto.setUsuarioId(vendedor.getUsuarioId());
                    dto.setSucursal(vendedor.getSucursal());
                    dto.setMetaMensual(vendedor.getMetaMensual());
                    dto.setNombre(vendedor.getNombre());
                    dto.setRut(vendedor.getRut());
                    dto.setAreaVentas(vendedor.getAreaVentas());

                    EntityModel<VendedorDTO> recurso = EntityModel.of(dto);

                    recurso.add(linkTo(methodOn(VendedorController.class).obtenerVendedorPorId(id)).withSelfRel());
                    recurso.add(linkTo(methodOn(VendedorController.class).listarTodosLosVendedores()).withRel("todosLosVendedores"));

                    return ResponseEntity.ok(recurso);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // GET con HATEOAS listar todos
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<VendedorDTO>>> listarTodosLosVendedores() {
        List<VendedorDTO> vendedores = vendedorService.obtenerTodosLosVendedoresDTO();

        List<EntityModel<VendedorDTO>> recursos = vendedores.stream()
                .map(v -> {
                    EntityModel<VendedorDTO> recurso = EntityModel.of(v);
                    recurso.add(linkTo(methodOn(VendedorController.class)
                            .obtenerVendedorPorId(v.getIdVendedor())).withSelfRel());
                    return recurso;
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<VendedorDTO>> coleccion = CollectionModel.of(recursos);
        coleccion.add(linkTo(methodOn(VendedorController.class).listarTodosLosVendedores()).withSelfRel());

        return ResponseEntity.ok(coleccion);
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
