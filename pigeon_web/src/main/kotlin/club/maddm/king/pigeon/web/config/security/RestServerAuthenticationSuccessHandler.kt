package club.maddm.king.pigeon.web.config.security

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import reactor.core.publisher.Mono
import java.awt.image.DataBuffer
import java.nio.charset.Charset

/**
 * @author  GG boy
 * @date  2020/7/27 14:31
 * @version 1.0
 */
class RestServerAuthenticationSuccessHandler : ServerAuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(webFilterExchange: WebFilterExchange, authentication: Authentication): Mono<Void> {
        val response = webFilterExchange.exchange.response
        response.statusCode = HttpStatus.OK
        response.headers.contentType = MediaType.APPLICATION_JSON_UTF8

        val body: String = "{'message':'登录成功！'}"
        val dataBuffer = response.bufferFactory().wrap(body.toByteArray(Charsets.UTF_8))
        return response.writeWith(Mono.just(dataBuffer))
    }
}