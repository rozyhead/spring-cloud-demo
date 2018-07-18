package com.github.rozyhead.springclouddemo.user.model

import javax.persistence.*

/**
 * オーガニゼーション。
 */
@Entity
data class Organization(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    var description: String = ""
) {

  fun addMember(user: User, role: MemberRole): Member {
    return Member(organizationId = this.id, userId = user.id, role = role)
  }

}