package com.example.filmsitesi.category.enums;

import com.example.filmsitesi.generic.exceptions.BaseErrorMessage;

public enum CategoryErrorMessage implements BaseErrorMessage {
    CATEGORY_DOES_NOT_EXİST("Category does not exist"),
    ;
    private String message;

    CategoryErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CategoryErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
