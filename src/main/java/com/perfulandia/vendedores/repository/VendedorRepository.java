package com.perfulandia.vendedores.repository;

import com.perfulandia.vendedores.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    List<Vendedor> findBySucursal(String sucursal);

}
