package beumbrella.controller;

import beumbrella.model.Product;
import beumbrella.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity add(@Valid @RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(product1.get().getId());
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-new-products/{id}")
    public ResponseEntity<Iterable<Product>> findNewProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findNewProduct(id), HttpStatus.OK);
    }

    @GetMapping("/find-products-by-category/{id}")
    public ResponseEntity<Iterable<Product>> findProductByCategories(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProductByCate(id), HttpStatus.OK);
    }
    @GetMapping("/find-products-by-address/{string}")
    public ResponseEntity<Iterable<Product>> findProductByAddress(@PathVariable String address) {
        return new ResponseEntity<>(productService.findProductByAddress(address), HttpStatus.OK);
    }

    @GetMapping("/find-products-by-name")
    public ResponseEntity<Iterable<Product>> findAllByNameContaining(@RequestParam String name) {
        Iterable<Product> products = productService.findAllByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
//
//    @GetMapping("/find-products-by-filter")
//    public ResponseEntity<Iterable<Product>> find(@RequestParam String name, @RequestParam String description, @RequestParam Integer from, @RequestParam Integer to, @RequestParam Long userId) {
//        return new ResponseEntity<>(productService.find('%' + name + '%', '%'+ description + '%', from, to, userId), HttpStatus.OK);
//    }

    @GetMapping("/find-products-by-filter")
    public ResponseEntity<Iterable<Product>> find(@RequestParam String name,@RequestParam String address, @RequestParam String description, @RequestParam Integer from, @RequestParam Integer to) {
        return new ResponseEntity<>(productService.find('%' + name + '%','%' + address + '%', '%'+ description + '%', from, to), HttpStatus.OK);
    }
    @GetMapping("/find-products-by-quantity")
    public ResponseEntity<Iterable<Product>> findbyquantity( @RequestParam Integer quantity) {
        return new ResponseEntity<>(productService.findbyquantity(quantity), HttpStatus.OK);
    }
    @GetMapping("/find-my-shop/{id}")
    public ResponseEntity<Iterable<Product>> findProductByUserId(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProductByUserId(id),HttpStatus.OK);
    }

    @GetMapping("/find-products-by-user-id-not/{id}")
    public ResponseEntity<Iterable<Product>> findAllByUserIdNot(@PathVariable Long id){
        return new ResponseEntity<>(productService.findAllByUserIdNot(id),HttpStatus.OK);
    }

    @GetMapping("/find-products-by-category-and-user-id-not/{categoryId}/{userId}")
    public ResponseEntity<Iterable<Product>> findProductByCategoryAndUserIdNot(@PathVariable Long categoryId, @PathVariable Long userId){
        return new ResponseEntity<>(productService.findProductByCategoryAndUserIdNot(categoryId, userId),HttpStatus.OK);
    }

    @GetMapping("/find-products-by-customer-id/{customerId}/{userId}")
    public ResponseEntity<Iterable<Product>> findProductsByCustomerId(@PathVariable Long customerId, @PathVariable Long userId){
        return new ResponseEntity<>(productService.findProductsByCustomerId(customerId, userId),HttpStatus.OK);
    }
}
