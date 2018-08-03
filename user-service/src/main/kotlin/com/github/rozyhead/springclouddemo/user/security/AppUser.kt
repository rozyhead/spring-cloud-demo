package com.github.rozyhead.springclouddemo.user.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User

class AppUser(
    val id: String,
    name: String,
    password: String? = null,
    authorities: Collection<GrantedAuthority>,
    val email: String
) : User(name, password, authorities) {

  constructor(id: String, name: String, password: String? = null, role: String, email: String) :
    this(id, name, password, listOf(SimpleGrantedAuthority("ROLE_$role")), email)

}
