package com.github.rozyhead.springclouddemo.common.security

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter

class AppUserAuthenticationConverter : DefaultUserAuthenticationConverter() {

  val log = LoggerFactory.getLogger(javaClass)!!

  override fun convertUserAuthentication(authentication: Authentication): MutableMap<String, *> {
    log.info("authentication: {}", authentication)
    val map= super.convertUserAuthentication(authentication)
    if (authentication.principal !is AppUser) {
      return map
    }

    val appUser = authentication.principal as AppUser
    val newMap = LinkedHashMap(map)
    newMap["user_id"] = appUser.id
    newMap["email"] = appUser.email
    return newMap
  }

  override fun extractAuthentication(map: MutableMap<String, *>): Authentication? {
    val authentication = super.extractAuthentication(map) ?: return null
    if (!map.containsKey("user_id")) {
      return authentication
    }
    val appUser = AppUser(map["user_id"].toString(), authentication.name, "N/A", authentication.authorities, map["email"].toString())
    return UsernamePasswordAuthenticationToken(appUser, appUser.password, appUser.authorities)
  }

}
