package club.maddm.king.pigeon.web.controller

import club.maddm.king.pigeon.model.entity.SysUser
import club.maddm.king.pigeon.web.service.CustomReactiveUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * @author  GG boy
 * @date  2020/7/28 19:35
 * @version 1.0
 */
@RestController
@RequestMapping("pigeon/user")
class UserController {

    @Autowired
    private lateinit var userService: CustomReactiveUserDetailsService

    @PostMapping
    fun registered(user: SysUser): Mono<Unit> {
        return userService.save(user)
    }
}