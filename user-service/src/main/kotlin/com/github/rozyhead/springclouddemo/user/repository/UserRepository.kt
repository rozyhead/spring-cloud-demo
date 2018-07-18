package com.github.rozyhead.springclouddemo.user.repository

import com.github.rozyhead.springclouddemo.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
  fun findByName(name: String): User?
}