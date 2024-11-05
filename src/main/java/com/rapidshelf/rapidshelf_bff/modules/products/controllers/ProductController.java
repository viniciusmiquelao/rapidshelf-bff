package com.rapidshelf.rapidshelf_bff.modules.products.controllers;

import com.rapidshelf.rapidshelf_bff.modules.products.dto.ProductRequestDTO;
import com.rapidshelf.rapidshelf_bff.modules.products.dto.ProductResponseDTO;
import com.rapidshelf.rapidshelf_bff.modules.products.dto.ProductStatusUpdateDTO;
import com.rapidshelf.rapidshelf_bff.modules.products.entities.Product;
import com.rapidshelf.rapidshelf_bff.modules.products.entities.ProductStatus;
import com.rapidshelf.rapidshelf_bff.modules.products.exceptions.InvalidProductStatusException;
import com.rapidshelf.rapidshelf_bff.modules.products.exceptions.ProductNotFoundException;
import com.rapidshelf.rapidshelf_bff.modules.products.repositories.ProductRepository;
import com.rapidshelf.rapidshelf_bff.modules.users.entities.User;
import com.rapidshelf.rapidshelf_bff.modules.users.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequestDTO body) {
        Product newProduct = new Product(body);


        String loggedInUserId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();


        newProduct.setUserId(loggedInUserId);


        repository.save(newProduct);


        URI location = URI.create("/products/" + newProduct.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productList = repository.findByStatus(ProductStatus.ACTIVED)
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/all")
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }


    @PatchMapping("/{productId}/status")
    public ResponseEntity<Product> updateProductStatus(
            @PathVariable String productId,
            @RequestBody @Valid ProductStatusUpdateDTO statusUpdateDTO
    ) {
        Product product = repository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        ProductStatus status = statusUpdateDTO.getStatus();
        if (status != ProductStatus.ACTIVED && status != ProductStatus.REPROVED) {
            throw new InvalidProductStatusException();
        }

        product.setStatus(status);
        repository.save(product);

        return ResponseEntity.ok(product);
    }
}
