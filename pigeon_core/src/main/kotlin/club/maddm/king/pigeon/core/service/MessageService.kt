package club.maddm.king.pigeon.core.service

import club.maddm.king.pigeon.core.mapper.MessageMapper
import club.maddm.king.pigeon.core.model.pojo.Message
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


/**
 * @author  GG boy
 * @date  2020/7/15 18:41
 * @version 1.0
 */
@Service
public class MessageService {

    @Autowired
    private lateinit var messageMapper: MessageMapper

    @Autowired
    private lateinit var reactiveMongoTemplate: ReactiveMongoTemplate

    fun queryMessage(receiver: String): Flux<Message> {
        return messageMapper.findByReceiver(receiver)
    }
}