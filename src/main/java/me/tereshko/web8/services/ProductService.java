package me.tereshko.web8.services;

import lombok.RequiredArgsConstructor;
import me.tereshko.web8.models.Product;
import me.tereshko.web8.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }
}
