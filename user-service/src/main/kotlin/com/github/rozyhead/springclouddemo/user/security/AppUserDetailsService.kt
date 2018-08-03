package com.github.rozyhead.springclouddemo.user.security

import com.github.rozyhead.springclouddemo.user.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class AppUserDetailsService(
    val userRepository: UserRepository
) : UserDetailsService {

  val log = LoggerFactory.getLogger(javaClass)!!

  override fun loadUserByUsername(username: String): UserDetails {
    log.info("loadUserByUsername: {}", username)
    val user = userRepository.findByName(username) ?: throw UsernameNotFoundException(username)
    return AppUser(user.id.toString(), user.name, user.password, user.role.name, user.email)
  }

}

