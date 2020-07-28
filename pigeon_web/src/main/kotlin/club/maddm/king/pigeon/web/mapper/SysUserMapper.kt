package club.maddm.king.pigeon.web.mapper

import club.maddm.king.pigeon.model.entity.SysUser
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.security.core.userdetails.UserDetails
import reactor.core.publisher.Mono

interface SysUserMapper : ReactiveMongoRepository<SysUser, String> {
    fun findByUsername(username: String): Mono<UserDetails>
}