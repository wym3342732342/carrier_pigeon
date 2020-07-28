package club.maddm.king.pigeon.core.model.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * 消息实体
 * @author  GG boy
 * @date  2020/7/15 15:53
 * @version 1.0
 */
@Document(collection = "message")
public data class Message(
    @Id var _id: String,//id
    var sender: String,//发送人
    var receiver: String,//接收人
    var info: String,//消息的主题信息
    var time: Date,//消息发送时间
    var receivingTime: Date//消息接收时间
) {

}