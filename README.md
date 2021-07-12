# his-plus2 
Neusoft Cloud HIS miroc service by Spring Cloud.

About version: Spring Cloud 2020.0.3 , Spring Boot 2.4.6 , JDK1.8 ,  scala 2.13.6, lombok 1.18.20

## Overview
  This is HIS back end micro service code. System architecture base Spring Cloud.
  
  Suggest but not forced the following technology stack:
  
  1、spring-data-jpa
  
  2、jackjson
  
  3、logback
  
  4、Hikari
  
  5、redisson
  
  6、Global uniform response format
  
  7、Global uniform exception handler
  
  8、springfox-swagger3.0

## Explain base support service
### 1.1 plus2-config
   Config micro service management all micro service config application.yml file.
   
   Config micro service current no register eureka service, only alone service.
   
   Global uniform log handler, to see /config/application-log.yml, other micro service will included.
### 1.2 plus2-eureka
   Eureka micro service start should specify start param '-Dspring.profiles.active=peer1', the param value rang 'peer1' or 'peer2' or 'peer3'
   
   Development: specify 'peer1'
   
   Production: should start multi micro service specify 'peer1'、'peer2'、'peer3', and set eureka.instance.hostname real value
### 1.3 plus2-core
   This is not real running micro service, only use common core code multiplex to other business micro service.
   
   Include:
   
     1、resultpack: handle global uniform response and exception
     2、scala util: integration common trait, e.g.: logger
     3、Swagger: integration io.springfox-swagger3.0, each micro service will produce each different swagger title. 
        Call url: http://{spring.application.name}:{server.port}/swagger-ui/index.html
     4、Redisson: integration redisson-spring-boot-starter, following three use model and more to see: https://github.com/redisson/redisson.git
        4.1 Use @Cacheable @CacheEvict @CachePut @Caching, to see https://spring.io/guides/gs/caching/ https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/cache.html
        4.2 @Autowired use RedisTemplate, to see https://spring.io/projects/spring-data-redis
        4.3 @Autowired use RedissonClient, to see https://github.com/redisson/redisson
### 1.4 plus2-org
   This micro service is check and use plus2-core code demo code by DDD.   
