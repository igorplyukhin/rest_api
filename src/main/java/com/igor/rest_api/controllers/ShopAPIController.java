package com.igor.rest_api.controllers;

import com.igor.rest_api.payload.request.CreateProductRequest;
import com.igor.rest_api.payload.response.IdResponse;
import com.igor.rest_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(ShopAPIControllerPaths.BASE_PATH)
public class ShopAPIController {
    @Autowired
    ProductService productService;

    @PostMapping(ShopAPIControllerPaths.CREATE)
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductRequest r) {
        var id = productService.createProduct(r.getSku(), r.getName(), r.getType(), r.getPrice());
        return new ResponseEntity<>(new IdResponse(id), HttpStatus.CREATED);
    }

    @DeleteMapping(ShopAPIControllerPaths.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(value = "idOrSku") String idOrSku) {
        productService.deleteProductByIdOrSku(idOrSku);
    }

    @GetMapping(ShopAPIControllerPaths.GET)
    public ResponseEntity<?> getProduct(@PathVariable(value = "idOrSku") String idOrSku){
        return new ResponseEntity<>(productService.getProductByIdOrSku(idOrSku), HttpStatus.OK);
    }

    @PutMapping(ShopAPIControllerPaths.PUT)
    public void putProduct(@RequestBody @Valid CreateProductRequest r, @PathVariable(value = "idOrSku") String idOrSku) {
        productService.updateProductByIdOrSku(idOrSku, r.getSku(), r.getName(), r.getType(), r.getPrice());
    }

    @GetMapping(ShopAPIControllerPaths.GET_ITEMS)
    public ResponseEntity<?> getAllProducts(@RequestParam(name = "offset") int offset, @RequestParam(name = "limit") int limit) {
        return new ResponseEntity<>(productService.getAllProducts(offset, limit), HttpStatus.OK);
    }
}

