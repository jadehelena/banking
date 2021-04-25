package com.jadehelena.banking.config.security

import com.jadehelena.banking.model.User
import com.jadehelena.banking.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService
    private UserRepository userRepository

    TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService
        this.userRepository = userRepository
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, java.io.IOException {
        String token = recoverToken(request)
        boolean isTokenValid = tokenService.isTokenValid(token)

        if(isTokenValid) {
            authenticateUser(token)
        }

        filterChain.doFilter(request, response)
    }

    private recoverToken(HttpServletRequest request){
        String token = request.getHeader("Authorization")
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null
        }

        return token.substring(7, token.length())
    }

    private void authenticateUser(String token) {
        Long userId = tokenService.getUserId(token)
        User user = userRepository.findById(userId).get()

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
        SecurityContextHolder.getContext().setAuthentication(authentication)
    }
}
