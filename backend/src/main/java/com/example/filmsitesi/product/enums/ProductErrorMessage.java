package com.example.filmsitesi.product.enums;

import com.example.filmsitesi.generic.exceptions.BaseErrorMessage;

public enum ProductErrorMessage implements BaseErrorMessage {
    PRODUCT_DOES_NOT_EXİST("Product does not exist")
    ;
    private String message;

    ProductErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProductErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
