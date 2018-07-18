package com.github.rozyhead.springclouddemo.user

import com.github.rozyhead.springclouddemo.user.model.User
import com.github.rozyhead.springclouddemo.user.model.UserRole
import com.github.rozyhead.springclouddemo.user.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SampleDataInitializer(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

  @Transactional
  override fun run(vararg args: String?) {
    registerUser("admin", UserRole.SYSTEM_ADMIN)
    registerUser("general")
    registerUser("viewer")
  }

  private fun registerUser(name: String, role: UserRole = UserRole.USER): User {
    val user = User(
        name = name,
        email = "$name@example.com",
        password = passwordEncoder.encode(name),
        role = role
    )
    userRepository.save(user)
    return user
  }

}