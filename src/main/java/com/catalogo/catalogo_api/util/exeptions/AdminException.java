package com.catalogo.catalogo_api.util.exeptions;

public class AdminException extends RuntimeException {

    public AdminException(String message) {
        super(message);
    }

    public static AdminException notFound(Long id) {
        return new AdminException(String.format("Admin with id %d not found.", id));
    }

}