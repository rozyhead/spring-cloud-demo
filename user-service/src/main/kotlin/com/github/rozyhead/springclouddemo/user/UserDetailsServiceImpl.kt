package com.github.rozyhead.springclouddemo.user

import com.github.rozyhead.springclouddemo.user.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(
    val userRepository: UserRepository
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository.findByName(username) ?: throw UsernameNotFoundException(username)
    val authorities = listOf(SimpleGrantedAuthority("ROLE_${user.role.name}"))
    return org.springframework.security.core.userdetails.User(user.name, user.password, authorities)
  }

}