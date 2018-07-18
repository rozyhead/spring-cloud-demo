package com.github.rozyhead.springclouddemo.user

import com.github.rozyhead.springclouddemo.user.model.*
import com.github.rozyhead.springclouddemo.user.repository.MemberRepository
import com.github.rozyhead.springclouddemo.user.repository.OrganizationRepository
import com.github.rozyhead.springclouddemo.user.repository.UserRepository
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SampleDataInitializer(
    val userRepository: UserRepository,
    val organizationRepository: OrganizationRepository,
    val memberRepository: MemberRepository,
    val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

  @Transactional
  override fun run(vararg args: String?) {
    val admin = registerUser("admin", UserRole.SYSTEM_ADMIN)
    val general = registerUser("general")
    val viewer = registerUser("viewer")

    val org1 = registerOrganization("org1")
    val org2 = registerOrganization("org2")

    registerMember(org1, admin, MemberRole.ADMIN)
    registerMember(org1, general, MemberRole.GENERAL)
    registerMember(org1, viewer, MemberRole.VIEWER)

    registerMember(org2, viewer, MemberRole.VIEWER)
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

  private fun registerOrganization(name: String): Organization {
    val org = Organization(name = name)
    organizationRepository.save(org)
    return org
  }

  private fun registerMember(org: Organization, user: User, role: MemberRole): Member {
    val member = org.addMember(user, role)
    memberRepository.save(member)
    return member
  }

}