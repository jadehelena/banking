package com.jadehelena.banking.controller.dto

class ExceptionDto {
    private String message

    ExceptionDto(String message) {
        this.message = message
    }

    String getMessage() {
        return message
    }

}
