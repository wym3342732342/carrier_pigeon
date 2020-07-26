package club.maddm.king.pigeon.core.model.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * 消息实体
 * @author  GG boy
 * @date  2020/7/15 15:53
 * @version 1.0
 */
@Document(collection = "message")
public data class Message(
    @Id var _id: String,
    var title: String
) {

}