package com.github.rozyhead.springclouddemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class SpringCloudDemoApplication

fun main(args: Array<String>) {
  runApplication<SpringCloudDemoApplication>(*args)
}