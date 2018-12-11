package com.netteans.cloud.exposed.service.kt

import com.netteans.domain.DemoUser
import feign.hystrix.FallbackFactory
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "service-zookeeper", path = "/", fallbackFactory = FallBackMethodsFactory::class)
interface FeignService {

    @RequestMapping(value = ["/get/{id}"], method = arrayOf(RequestMethod.GET))
    fun inst(@PathVariable("id") id: Int?): Any

    @RequestMapping(value = ["/timeout"], method = arrayOf(RequestMethod.GET))
    fun timeout(): Any
}

@Component
internal class FallBackMethodsFactory : FallbackFactory<FeignService> {

    var logger = LoggerFactory.getLogger(FallBackMethodsFactory::class.java)

    override fun create(throwable: Throwable): FeignService {
        return object : FeignService {

            override fun inst(id: Int?): ResponseEntity<*> {
                logger.error("inst {} fallback", this)
                val demoUser = DemoUser()
                demoUser.email = "timeout"
                demoUser.password = "timeout"
                demoUser.name = "timeout"
                return ResponseEntity(demoUser, HttpStatus.GATEWAY_TIMEOUT)
            }

            override fun timeout(): ResponseEntity<*> {
                logger.info("timeout {} fallback", this)
                return ResponseEntity(Integer.MIN_VALUE, HttpStatus.GATEWAY_TIMEOUT)
            }
        }
    }
}