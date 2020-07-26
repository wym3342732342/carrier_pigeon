import club.maddm.king.pigeon.core.PigeonApplication
import club.maddm.king.pigeon.core.model.pojo.Message
import club.maddm.king.pigeon.core.service.MessageService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.AfterTest
import kotlin.test.Test

/**
 * @author  GG boy
 * @date  2020/7/15 16:21
 * @version 1.0
 */
@SpringBootTest(classes = [PigeonApplication::class])
@RunWith(SpringRunner::class)
public class MongoTest {

    @Autowired
    private lateinit var messageService: MessageService

    @AfterTest
    fun after() {
        Thread.sleep(1000)
    }


    @Test
    fun insert() {
        messageService.testMapperSave(Message("12391239129932","test事务")).subscribe {
            println(it)
        }
    }

    @Test
    fun remAll() {
        messageService.testRemoveALl().subscribe{
            println(it.deletedCount)
        }
    }

    @Test
    fun find() {
        messageService.selectAll().subscribe{
            print(it)
        }
    }
}