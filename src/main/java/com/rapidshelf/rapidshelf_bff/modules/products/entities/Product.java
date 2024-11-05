package com.rapidshelf.rapidshelf_bff.modules.products.entities;

import com.rapidshelf.rapidshelf_bff.modules.products.dto.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private ProductStatus status;

    private String userId;

    private Integer price;

    public Product(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
        this.description = data.description();
        this.status = ProductStatus.PENDING;
    }

    @PrePersist
    private void prePersist() {
        if (this.status == null) {
            this.status = ProductStatus.PENDING;
        }
    }
}