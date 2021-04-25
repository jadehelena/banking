package com.jadehelena.banking.config.security

import com.jadehelena.banking.model.User
import com.jadehelena.banking.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Service
class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username)
        if (user.isPresent()) {
            return user.get()
        }

        throw new UsernameNotFoundException("Invalid username or password")
    }
}
