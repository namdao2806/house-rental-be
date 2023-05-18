package beumbrella.service.impl;

import beumbrella.model.Product;
import beumbrella.repository.ProductRepository;
import beumbrella.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findNewProduct(Long id) {
        return productRepository.findNewProduct(id);
    }

    @Override
    public Iterable<Product> findProductByCate(long id) {
        return productRepository.findProductByCategory(id);
    }

    @Override
    public Iterable<Product> findProductByUserId(Long id) {
        return productRepository.findProductByUserId(id);
    }

    @Override
    public Iterable<Product> findProductByCategoryAndUserIdNot(Long categoryId, Long userId) {
        return productRepository.findProductByCategoryAndUserIdNot(categoryId, userId);
    }

    @Override
    public Iterable<Product> findProductsByCustomerId(Long customerId, Long userId) {
        return productRepository.findProductsByCustomerId(customerId, userId);
    }


    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllByUserIdNot(Long id) {
        return productRepository.findAllByUserIdNot(id);
    }

//    @Override
//    public Iterable<Product> find(String name, String description, Integer from, Integer to, Long userId) {
//        return productRepository.find(name,description, from, to, userId);
//    }
    @Override
    public Iterable<Product> find(String name,String address, String description, Integer from, Integer to) {
        return productRepository.find(name,address,description, from, to);
    }


}
