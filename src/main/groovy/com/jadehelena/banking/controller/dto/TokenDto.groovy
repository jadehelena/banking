package com.jadehelena.banking.controller.dto

class TokenDto {
    private String token
    private String type

    TokenDto(String token, String type) {
        this.token = token
        this.type = type
    }

    String getToken() {
        return token
    }

    String getType() {
        return type
    }
}
