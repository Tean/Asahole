package com.netteans.cloud.exposed.service.kt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class FeignController {
    private val INSTANCE_UUID = UUID.randomUUID()

    @Autowired
    var feignService: FeignService? = null

    @Value("\${version}")
    private val version: String? = null

    @Value("\${git.version}")
    private val git: String? = null

    @Value("\${server.port}")
    private val port: String? = null

    @Value("\${spring.profiles.active}")
    private val profiles: String? = null

    @RequestMapping(value = ["/get/{id}", "/get"], method = [RequestMethod.GET, RequestMethod.DELETE])
    fun remoteInstance(@PathVariable id: Int?): Any {
        return feignService!!.inst(id)
    }

    @GetMapping(value = ["/instance"])
    fun instance(): Any {
        return INSTANCE_UUID.toString() + " serve @ port " + port + " profiles: " + profiles + " git: " + git + " ver: " + version
    }

    @GetMapping(value = ["/timeout"])
    fun timeoutTest(): Any {
        return feignService!!.timeout()
    }
}