# his-plus2 
Neusoft Cloud HIS miroc service by Spring Cloud.

Main version: Spring Cloud 2020.0.3 , Spring Boot 2.4.6 , JDK1.8 ,  scala 2.13.6, lombok 1.18.10

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

## Explain base support service
### 1.1 plus2-config
   Config micro service management all micro service config application.yml file.
   
   Config micro service current no register eureka service, only alone service.
### 1.2 plus2-eureka
   Eureka micro service start should specify start param '-Dspring.profiles.active=peer1', the param value rang 'peer1' or 'peer2' or 'peer3'
   
   Development: specify 'peer1'
   
   Production: should start multi micro service specify 'peer1'、'peer2'、'peer3', and set eureka.instance.hostname real value
### 1.3 plus2-core
   This is not real running micro service, only use common core code multiplex to other business micro service.
### 1.4 plus2-org
   This micro service is check and use plus2-core code demo code by DDD.   
