package com.jadehelena.banking.controller

import com.jadehelena.banking.config.security.TokenService
import com.jadehelena.banking.controller.dto.TokenDto
import com.jadehelena.banking.controller.form.LoginForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

import javax.transaction.Transactional

@RestController
@RequestMapping('auth')
class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager

    @Autowired
    private TokenService tokenService

    @PostMapping
    @Transactional
    ResponseEntity<TokenDto> save(@RequestBody LoginForm loginForm, UriComponentsBuilder uriBuilder) {
        UsernamePasswordAuthenticationToken loginData = loginForm.convertToAuthenticationToken()

        try{
            Authentication authentication = authenticationManager.authenticate(loginData)
            String token = tokenService.generateToken(authentication)

            return ResponseEntity.ok(new TokenDto(token, "Bearer"))
        } catch(AuthenticationException e){
            return ResponseEntity.badRequest().build()
        }
    }
}
