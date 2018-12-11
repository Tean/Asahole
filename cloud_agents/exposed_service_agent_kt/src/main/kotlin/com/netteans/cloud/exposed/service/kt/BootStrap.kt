package com.netteans.cloud.exposed.service.kt

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.cloud.client.SpringCloudApplication
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.openfeign.EnableFeignClients

//@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
open class BootStrap {

    //    @Value("${server.port}")
    private fun setPort(p: String) {
        port = p
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BootStrap::class.java)
        private var port: String? = null

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BootStrap::class.java, *args)
            logger.info("port is {}", port) //启动之后才会注入
        }
    }
}
