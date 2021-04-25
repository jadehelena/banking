package com.jadehelena.banking.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.FetchType

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.ManyToMany

@Entity
class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    private String name
    private String password

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>()

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    void setPassword(String password) {
        this.password = password
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
    }

    @Override
    String getPassword() {
        return this.password
    }

    @Override
    String getUsername() {
        return this.name
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }
}
