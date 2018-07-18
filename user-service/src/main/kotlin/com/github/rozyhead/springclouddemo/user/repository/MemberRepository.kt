package com.github.rozyhead.springclouddemo.user.repository

import com.github.rozyhead.springclouddemo.user.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>
