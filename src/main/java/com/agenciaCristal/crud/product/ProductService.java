package com.agenciaCristal.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    HashMap<String, Object> datos;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<ProductModel> getProducts(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(ProductModel productModel) {
        Optional<ProductModel> res = productRepository.findProductByNombre(productModel.getNombre());
        datos = new HashMap<>();

        if(res.isPresent() && productModel.getId() == null){
            datos.put("error", true);
            datos.put("message", "El producto ya existe");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }
        datos.put("message", "El producto ha sido creado");
        if(productModel.getId() != null){
            datos.put("message", "El producto ha sido actualizado");
        }
        productRepository.save(productModel);
        datos.put("data", productModel);
        return new ResponseEntity<>(datos, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteProduct(Long id){
        datos = new HashMap<>();
        boolean existe = this.productRepository.existsById(id);
        if(!existe){
            datos.put("error", true);
            datos.put("message", "El producto no existe con ese id");
            return new ResponseEntity<>(datos, HttpStatus.BAD_REQUEST);
        }
        productRepository.deleteById(id);
        datos.put("message", "El producto ha sido eliminado");
        return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);

    }
}
