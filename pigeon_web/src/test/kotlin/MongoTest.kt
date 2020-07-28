import club.maddm.king.pigeon.core.model.pojo.Message
import club.maddm.king.pigeon.core.service.MessageService
import club.maddm.king.pigeon.PigeonApplication
import club.maddm.king.pigeon.core.mapper.MessageMapper
import club.maddm.king.pigeon.model.entity.SysUser
import club.maddm.king.pigeon.web.mapper.SysUserMapper
import club.maddm.king.pigeon.web.util.CodecUtils
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
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
    @Autowired
    private lateinit var messageMapper: MessageMapper
    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @AfterTest
    fun after() {
        Thread.sleep(1000)
    }

    @Test
    fun insertMessage(){
        messageMapper.save(Message("456", "ww", "789", "112", Date(), Date())).subscribe()
    }
    @Test
    fun insertUser(){
        val sysUser = SysUser()
        sysUser.id = "123456"
        sysUser.username = "0202"
        sysUser.password = CodecUtils.bCryptHashpw("123456")
        sysUserMapper.save(sysUser).subscribe()
    }
    @Test
    fun removeUser() {
        sysUserMapper.deleteById("123").subscribe()
    }
}