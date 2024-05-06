package com.agenciaCristal.crud.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/products")

public class ProductController {

    private final  ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductModel> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProduct(@RequestBody ProductModel productModel){
        return this.productService.newProduct(productModel);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarProduct(@RequestBody ProductModel productModel){
        return this.productService.newProduct(productModel);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> eliminarProduct(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);
    }


}
