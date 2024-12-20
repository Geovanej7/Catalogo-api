package com.catalogo.catalogo_api.util.exeptions;

public class AdminException extends RuntimeException {

    public AdminException(String message) {
        super(message);
    }

    public static AdminException notFound(Long id) {
        return new AdminException(String.format("Admin with id %d not found.", id));
    }
    public static AdminException emailNotFound(String email) {
        return new AdminException(String.format("email not found", email));
    }

    public static AdminException errorSendingEmail() {
        return new AdminException(String.format("eror sending email"));
    }

    public static AdminException notMatch() {
        return new AdminException(String.format("Passwords do not match"));
    }

}
