package com.github.rozyhead.springclouddemo.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@SpringBootApplication
@EnableAuthorizationServer
class SpringCloudDemoApplication

fun main(args: Array<String>) {
  runApplication<SpringCloudDemoApplication>(*args)
}