package me.tereshko.web8.controllers;

import lombok.RequiredArgsConstructor;
import me.tereshko.web8.models.Product;
import me.tereshko.web8.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/api/v1/")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> findAllProducts(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {
        List<Product> productList = new ArrayList<Product>();
        Pageable paging = PageRequest.of(page, size);
        Page<Product> productPage = productService.findAll(paging);
        productList = productPage.getContent();


        Map<String, Object> response = new HashMap<>();
        response.put("products", productList);
        response.put("currentPage", productPage.getNumber());
        response.put("totalProducts", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @GetMapping("/remove/{id}")
    public void removeProductById(@PathVariable Long id) {
        productService.removeProductById(id);
    }

}
