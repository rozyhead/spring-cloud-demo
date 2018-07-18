package com.github.rozyhead.springclouddemo.user.model

import javax.persistence.*

/**
 * ユーザー。
 */
@Entity
data class User(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: UserRole = UserRole.USER
)
