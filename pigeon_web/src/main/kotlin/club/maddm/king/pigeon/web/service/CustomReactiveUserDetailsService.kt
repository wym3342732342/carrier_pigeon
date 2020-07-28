package club.maddm.king.pigeon.web.service

import club.maddm.king.pigeon.model.entity.SysUser
import club.maddm.king.pigeon.web.mapper.SysUserMapper
import club.maddm.king.pigeon.web.util.CodecUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomReactiveUserDetailsService : ReactiveUserDetailsService {
    @Autowired
    private lateinit var sysUserRepository: SysUserMapper

    override fun findByUsername(username: String): Mono<UserDetails> {
        return sysUserRepository.findByUsername(username)
            .switchIfEmpty(Mono.error(UsernameNotFoundException("User Not Found")))
            .map { obj: UserDetails ->
                UserDetails::class.java.cast(
                    obj
                )
            }
    }

    fun save(user: SysUser): Mono<Unit> {
        user.password = CodecUtils.bCryptHashpw(user.password)//加密
        return sysUserRepository.save(user).thenReturn(Unit)
    }
}