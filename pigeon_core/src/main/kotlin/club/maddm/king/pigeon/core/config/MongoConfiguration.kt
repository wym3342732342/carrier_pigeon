package club.maddm.king.pigeon.core.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class MongoConfiguration /*: AbstractMongoClientConfiguration()*/ {
    /**
     * 开启mongo事务
     */
    @Bean
    fun transactionManager(dbFactory: ReactiveMongoDatabaseFactory)
            : ReactiveMongoTransactionManager {
        return ReactiveMongoTransactionManager(dbFactory)
    }
}