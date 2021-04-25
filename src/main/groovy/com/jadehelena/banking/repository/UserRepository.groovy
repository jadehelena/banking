package com.jadehelena.banking.repository

import com.jadehelena.banking.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByName(String name)
}