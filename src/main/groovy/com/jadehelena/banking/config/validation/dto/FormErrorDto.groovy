package com.jadehelena.banking.config.validation.dto

class FormErrorDto {
    private String field
    private String errorMessage

    FormErrorDto(String field, String errorMessage){
        this.field = field
        this.errorMessage = errorMessage
    }

    String getField() {
        return field
    }

    String getErrorMessage() {
        return errorMessage
    }
}
