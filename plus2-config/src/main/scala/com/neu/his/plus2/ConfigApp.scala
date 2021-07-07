package com.neu.his.plus2

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class ConfigApp
object ConfigApp extends App{
  SpringApplication.run(classOf[ConfigApp], args: _*)
}
