package com.neu.his.plus2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EurekaApp
object EurekaApp extends App{
  SpringApplication.run(classOf[EurekaApp], args: _*)
}
