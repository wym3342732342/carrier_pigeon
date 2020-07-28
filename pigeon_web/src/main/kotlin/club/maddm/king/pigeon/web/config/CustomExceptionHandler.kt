package club.maddm.king.pigeon.web.config

import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono

/**
 * 通用异常处理
 */
@RestControllerAdvice
class CustomExceptionHandler {
    private val logger = LogFactory.getLog(javaClass)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.OK)
    fun handleCustomException(e: Exception): Mono<Unit> {
        logger.error(e.message)
        return Mono.just(Unit)
    }
}