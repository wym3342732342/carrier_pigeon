package club.maddm.king.pigeon.web.config

import club.maddm.king.pigeon.web.config.security.RestServerAuthenticationFailureHandler
import club.maddm.king.pigeon.web.config.security.RestServerAuthenticationSuccessHandler
import club.maddm.king.pigeon.web.service.CustomReactiveUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * @author  GG boy
 * @date  2020/7/27 11:57
 * @version 1.0
 */
@Configuration
@EnableReactiveMethodSecurity
class ReactiveSecurityConfig {


    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .cors().configurationSource(object : CorsConfigurationSource {
                override fun getCorsConfiguration(exchange: ServerWebExchange): CorsConfiguration {
                    val config = CorsConfiguration()
                    //1) 允许的域,不要写*，否则cookie就无法使用了
                    config.addAllowedOrigin("*")
                    //        config.addAllowedOrigin("http://localhost:9000")
                    //2) 是否发送Cookie信息
                    config.allowCredentials = true
                    //3) 允许的请求方式
                    config.addAllowedMethod("OPTIONS")
                    config.addAllowedMethod("HEAD")
                    config.addAllowedMethod("GET")
                    config.addAllowedMethod("PUT")
                    config.addAllowedMethod("POST")
                    config.addAllowedMethod("DELETE")
                    config.addAllowedMethod("PATCH")
                    // 4）允许的头信息
                    config.addAllowedHeader("*")

                    //2.添加映射路径，我们拦截一切请求
                    val configSource =
                        UrlBasedCorsConfigurationSource()
                    configSource.registerCorsConfiguration("/**", config)
                    return config
                }
            })

                .and()
            .csrf().disable()//关闭csrf
            .httpBasic().disable()//关闭http基本身份验证
            .exceptionHandling()
                .authenticationEntryPoint(object : ServerAuthenticationEntryPoint {
                    override fun commence(exchange: ServerWebExchange, e: AuthenticationException): Mono<Void> {
                        val response = exchange.response
                        response.statusCode = HttpStatus.UNAUTHORIZED//401请输入正确的账号密码再访问
                        response.headers.contentType = MediaType.APPLICATION_JSON_UTF8
                        val body: String = "{'message':'${e.message}'}"
                        val dataBuffer = response.bufferFactory().wrap(body.toByteArray(Charsets.UTF_8))
                        return response.writeWith(Mono.just(dataBuffer))
                    }

                })
                .accessDeniedHandler(object: ServerAccessDeniedHandler {
                    override fun handle(exchange: ServerWebExchange, exception: AccessDeniedException): Mono<Void> {
                        val response = exchange.response
                        response.statusCode = HttpStatus.UNAUTHORIZED//401请输入正确的账号密码再访问
                        response.headers.contentType = MediaType.APPLICATION_JSON_UTF8
                        val body: String = "{'message':'${exception.message}'}"
                        val dataBuffer = response.bufferFactory().wrap(body.toByteArray(Charsets.UTF_8))
                        return response.writeWith(Mono.just(dataBuffer))
                    }
                })
                .and()
            .formLogin()
                .authenticationSuccessHandler(RestServerAuthenticationSuccessHandler())
                .authenticationFailureHandler(RestServerAuthenticationFailureHandler())
                .and()
            .logout()
                .and()
            .authorizeExchange()
                .pathMatchers("/pigeon/user").permitAll()
                .anyExchange().authenticated()
            .and()
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder();
    }
}