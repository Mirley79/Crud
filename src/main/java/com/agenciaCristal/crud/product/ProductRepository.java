package com.agenciaCristal.crud.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {


    Optional<ProductModel> findProductByNombre(String nombre);
}
