package com.github.rozyhead.springclouddemo.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@SpringBootApplication
class UserServiceApplication {

  @Configuration
  @EnableGlobalMethodSecurity(prePostEnabled = true)
  class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManager(): AuthenticationManager {
      return super.authenticationManager()
    }

    override fun configure(http: HttpSecurity) {
      http.authorizeRequests()
          .anyRequest().authenticated()
          .and()
//          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//          .and()
          .httpBasic().realmName("demo")
          .and()
          .csrf().disable()
          .headers().frameOptions().disable()
          .and()
          .formLogin()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  }

  @Configuration
  @EnableAuthorizationServer
  class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun configure(clients: ClientDetailsServiceConfigurer) {
      clients.inMemory()
          .withClient("task-service")
          .secret(passwordEncoder.encode("task-service"))
          .authorizedGrantTypes("password", "authorization_code")
          .scopes("openid", "task.read", "task.write")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
      val tokenEnhancerChain = TokenEnhancerChain()
      tokenEnhancerChain.setTokenEnhancers(listOf(accessTokenConverter()))

      endpoints.tokenStore(tokenStore())
          .accessTokenConverter(accessTokenConverter())
          .tokenEnhancer(tokenEnhancerChain)
          .authenticationManager(authenticationManager)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
      security.passwordEncoder(passwordEncoder)
          .checkTokenAccess("isAuthenticated()")
          .realm("demo")
    }

    @Bean
    fun tokenStore(): TokenStore = JwtTokenStore(accessTokenConverter())

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
      val converter = JwtAccessTokenConverter()
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
  runApplication<UserServiceApplication>(*args)
}