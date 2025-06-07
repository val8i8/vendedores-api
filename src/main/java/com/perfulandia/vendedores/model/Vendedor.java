package com.perfulandia.vendedores.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendedores")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Vendedor {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String sucursal;

    @Column(name = "meta_mensual")
    private Double metaMensual;

    private String nombre;

    private String rut;

    @Column(name = "area_ventas")
    private String areaVentas;

}
