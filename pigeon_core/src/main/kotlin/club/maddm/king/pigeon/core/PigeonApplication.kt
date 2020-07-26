package club.maddm.king.pigeon.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 *
 * @author  GG boy
 * @date  2020/7/15 16:11
 * @version 1.0
 */
@SpringBootApplication
@EnableTransactionManagement
class PigeonApplication {
}
fun main(args: Array<String>) {
    runApplication<PigeonApplication>(*args)
}
