package club.maddm.king.pigeon.web.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * 全局Cors配置,跨域问题,可配置在security中的.cors().configurationSource()
 */
//@Configuration
class GlobalCorsConfig {
//    @Bean
    fun corsFilter(): CorsFilter {
        //1.添加CORS配置信息
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

        //3.返回新的CorsFilter.
        return CorsFilter(configSource)
    }
}