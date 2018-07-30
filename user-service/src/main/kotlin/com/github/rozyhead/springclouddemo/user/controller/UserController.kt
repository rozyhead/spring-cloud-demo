package com.github.rozyhead.springclouddemo.user.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController {

  @GetMapping("/user")
  fun getUser(principal: Principal): Principal {
    return  principal
  }

}