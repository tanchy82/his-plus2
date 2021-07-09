package com.neu.his.plus2.core.resultpack

import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody}

import scala.beans.BeanProperty

/**
  * @author tanchy
  *   Unified format wrap exception json message.
  */
@ControllerAdvice
class ExceptionWrap {

  @ResponseBody
  @ExceptionHandler(value = Array(classOf[Exception], classOf[GlobalException]))
  def exceptionHandler(h:HttpServletRequest, e: Throwable): ResultMessage = {
    e match {
      case g:GlobalException => ResultMessage(g.code, g.msg, g.getMessage)
      case _ => ResultMessage(-1, "error", e.getMessage)
    }
  }

}

case class GlobalException(@BeanProperty code: Int = -1, @BeanProperty msg: String = "An exception occurs.") extends RuntimeException
