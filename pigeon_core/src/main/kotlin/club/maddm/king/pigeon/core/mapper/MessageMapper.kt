package club.maddm.king.pigeon.core.mapper

import club.maddm.king.pigeon.core.model.pojo.Message
import club.maddm.king.pigeon.core.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.mongodb.repository.Tailable
import reactor.core.publisher.Flux

/**
 * @author  GG boy
 * @date  2020/7/15 16:27
 * @version 1.0
 */
interface MessageMapper : ReactiveMongoRepository<Message, String> {

    /**
     * 根据接受人查消息
     */
    @Tailable
    fun findByReceiver(receiver: String): Flux<Message>

}