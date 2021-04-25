package com.jadehelena.banking.controller.form

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class LoginForm {
    private String username
    private String password

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    def convertToAuthenticationToken(){
        return new UsernamePasswordAuthenticationToken(username, password)
    }
}
