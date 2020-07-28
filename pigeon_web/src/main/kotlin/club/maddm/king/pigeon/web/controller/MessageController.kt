package club.maddm.king.pigeon.web.controller

import club.maddm.king.pigeon.core.model.pojo.Message
import club.maddm.king.pigeon.core.service.MessageService
import club.maddm.king.pigeon.model.entity.SysUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.web.savedrequest.SavedRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author  GG boy
 * @date  2020/7/27 10:11
 * @version 1.0
 */
@RestController
@RequestMapping("pigeon/message")
class MessageController {
    @Autowired
    private lateinit var messageService: MessageService

    @GetMapping(produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun queryMessage(receiver: String): Flux<Message> {
        return messageService.queryMessage(receiver)
    }
}