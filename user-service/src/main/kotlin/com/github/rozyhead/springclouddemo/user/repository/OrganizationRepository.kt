package com.github.rozyhead.springclouddemo.user.repository

import com.github.rozyhead.springclouddemo.user.model.Organization
import org.springframework.data.jpa.repository.JpaRepository

interface OrganizationRepository : JpaRepository<Organization, Long>