package com.rapidshelf.rapidshelf_bff.modules.products.entities;

public enum ProductStatus {
    ACTIVED("actived"),
    REPROVED("reproved"),
    PENDING("pending");

    private String status;

    ProductStatus(String role){
        this.status = status;
    }

    public String getRole(){
        return this.status;
    }
}
