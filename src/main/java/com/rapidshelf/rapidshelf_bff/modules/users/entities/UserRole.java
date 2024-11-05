package com.rapidshelf.rapidshelf_bff.modules.users.entities;

public enum UserRole {
    ADMIN("admin"),
    SELLER("seller"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
