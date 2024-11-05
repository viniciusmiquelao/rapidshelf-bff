package com.rapidshelf.rapidshelf_bff.modules.products.repositories;

import com.rapidshelf.rapidshelf_bff.modules.products.entities.Product;
import com.rapidshelf.rapidshelf_bff.modules.products.entities.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByStatus(ProductStatus status);
}
