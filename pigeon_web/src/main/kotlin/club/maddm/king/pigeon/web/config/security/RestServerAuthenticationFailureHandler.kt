package club.maddm.king.pigeon.web.config.security

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler
import reactor.core.publisher.Mono

/**
 * @author  GG boy
 * @date  2020/7/27 14:38
 * @version 1.0
 */
class RestServerAuthenticationFailureHandler : ServerAuthenticationFailureHandler {
    override fun onAuthenticationFailure(webFilterExchange: WebFilterExchange, exception: AuthenticationException): Mono<Void> {
        val response = webFilterExchange.exchange.response
        response.statusCode = HttpStatus.UNAUTHORIZED//401请输入正确的账号密码再访问
        response.headers.contentType = MediaType.APPLICATION_JSON_UTF8
        val body: String = "{'message':'${exception.message}'}"
        val dataBuffer = response.bufferFactory().wrap(body.toByteArray(Charsets.UTF_8))
        return response.writeWith(Mono.just(dataBuffer))
    }
}