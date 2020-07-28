package club.maddm.king.pigeon.web.util

import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * 加密工具类
 */
object CodecUtils {
    /**
     * md5加密
     * @param data 即将加密的数据
     * @param salt 盐
     * @return
     */
    @Deprecated("")
    fun md5Hex(data: String, salt: String): String {
        var salt = salt
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode().toString() + ""
        }
        return DigestUtils.md5Hex(
            salt + DigestUtils.md5Hex(
                data
            )
        )
    }

    /**
     * md5 16进制加密
     * @param data 即将加密的数据
     * @param salt 盐
     * @return
     */
    @Deprecated("")
    fun shaHex(data: String, salt: String): String {
        var salt = salt
        if (StringUtils.isBlank(salt)) {
            salt = data.hashCode().toString() + ""
        }
        return DigestUtils.sha512Hex(
            salt + DigestUtils.sha512Hex(
                data
            )
        )
    }

    /**
     * 生成盐的方法
     * @return
     */
    fun generateSalt(): String {
        return BCrypt.gensalt(12)
    }

    /**
     * bCrypt方式加密
     * @param data 即将加密的数据
     * @return
     */
    fun bCryptHashpw(data: String): String {
        return BCrypt.hashpw(data, BCrypt.gensalt())
    }

    /**
     * bCrypt方式加密
     * @param data 即将加密的数据
     * @return
     */
    fun bCryptHashpw(data: String, salt: String): String {
        return BCrypt.hashpw(data, salt)
    }

    /**
     * 比较是否相同
     * @param password 输入的密码
     * @param hashed 加密后的密码
     * @return
     */
    fun bCryptCheckpw(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }
}