package com.github.rozyhead.springclouddemo.task

import com.github.rozyhead.springclouddemo.common.security.AppUserAuthenticationConverter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@SpringBootApplication
class TaskServiceApplication {

  @Configuration
  @EnableResourceServer
  @EnableGlobalMethodSecurity(prePostEnabled = true)
  class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
      http.authorizeRequests().anyRequest().authenticated()
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
      resources.tokenStore(tokenStore())
    }

    @Bean
    fun tokenStore(): TokenStore = JwtTokenStore(accessTokenConverter())

    @Bean
    fun appUserAuthenticationConverter(): UserAuthenticationConverter {
      return AppUserAuthenticationConverter()
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
      val internalAccessTokenConverter = DefaultAccessTokenConverter()
      internalAccessTokenConverter.setUserTokenConverter(appUserAuthenticationConverter())

      val converter = JwtAccessTokenConverter()
      converter.accessTokenConverter = internalAccessTokenConverter
      converter.setSigningKey("123")
      return converter
    }

    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices {
      val defaultTokenServices = DefaultTokenServices()
      defaultTokenServices.setTokenStore(tokenStore())
      defaultTokenServices.setSupportRefreshToken(true)
      return defaultTokenServices
    }

  }
}

fun main(args: Array<String>) {
  runApplication<TaskServiceApplication>(*args)
}