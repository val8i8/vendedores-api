package com.perfulandia.vendedores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VendedorDTO {
    private Integer idVendedor;
    private Long usuarioId;
    private String sucursal;
    private Double metaMensual;
    private String nombre;
    private String rut;
    private String areaVentas;
   
}
