package com.example.filmsitesi.generic.exceptions;

public enum GeneralErrorMessage implements BaseErrorMessage{
    VALUES_CANNOT_BE_NULL("Values cannot be null"),
    ID_NOT_EQUAL("Given id does not equal with given object's")
    ;
    private String message;

    GeneralErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "GeneralErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return message;
    }
}
