package com.github.rozyhead.springclouddemo.user.model

import javax.persistence.*

/**
 * メンバー。
 */
@Entity
@Table(indexes = [
  Index(columnList = "organizationId, userId", unique = true),
  Index(columnList = "userId, organizationId")
])
data class Member(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    var organizationId: Long = 0,

    @Column(nullable = false)
    var userId: Long = 0,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var role: MemberRole = MemberRole.VIEWER
)

