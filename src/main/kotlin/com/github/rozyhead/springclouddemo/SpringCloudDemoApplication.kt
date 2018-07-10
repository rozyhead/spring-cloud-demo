package com.github.rozyhead.springclouddemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudDemoApplication>(*args)
}
