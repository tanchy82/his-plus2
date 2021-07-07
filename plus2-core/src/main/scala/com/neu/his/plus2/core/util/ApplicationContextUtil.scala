package com.neu.his.plus2.core.util

import org.springframework.context.{ApplicationContext, ApplicationContextAware}
import org.springframework.stereotype.Component

@Component
class ApplicationContextUtil extends ApplicationContextAware{

  override def setApplicationContext(applicationContext: ApplicationContext) =
    ApplicationContextUtil.context = applicationContext;

}
object ApplicationContextUtil {

  private var context: ApplicationContext = _

  def getApplicationContext(): ApplicationContext = context

  def getBeanByClass[T](t: Class[T]): T = context.getBean(t)

}
