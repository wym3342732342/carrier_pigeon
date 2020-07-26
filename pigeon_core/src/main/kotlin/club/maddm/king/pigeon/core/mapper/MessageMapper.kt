package club.maddm.king.pigeon.core.mapper

import club.maddm.king.pigeon.core.model.pojo.Message
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

/**
 * @author  GG boy
 * @date  2020/7/15 16:27
 * @version 1.0
 */
interface MessageMapper : ReactiveMongoRepository<Message, String> {

}