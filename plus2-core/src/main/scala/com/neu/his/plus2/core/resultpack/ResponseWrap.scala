package com.neu.his.plus2.core.resultpack

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.{HandlerMethodReturnValueHandler, ModelAndViewContainer}
import org.springframework.web.servlet.mvc.method.annotation.{RequestMappingHandlerAdapter, RequestResponseBodyMethodProcessor}

import scala.beans.BeanProperty
import org.springframework.data.domain.Page
import scala.jdk.CollectionConverters._

/**
  * @author tanchy
  *   Unified format wrap http response json message.
  */
@Configuration
class ResponseWrap extends InitializingBean{

  @Autowired
  var requestMappingHandlerAdapter: RequestMappingHandlerAdapter = null

  override def afterPropertiesSet(): Unit = {
    var h = requestMappingHandlerAdapter.getReturnValueHandlers.asScala.toBuffer
    (0 until h.size).foreach(i => {
      h(i) match {
        case v:RequestResponseBodyMethodProcessor => h(i) = new ResponseBodyWrapHandler(v)
        case _ =>
      }
    })
    requestMappingHandlerAdapter.setReturnValueHandlers(h.asJava)
  }
}

class ResponseBodyWrapHandler(delegate: HandlerMethodReturnValueHandler) extends HandlerMethodReturnValueHandler{

  override def supportsReturnType(methodParameter: MethodParameter): Boolean = delegate.supportsReturnType(methodParameter)

  override def handleReturnValue(o: Any, methodParameter: MethodParameter, modelAndViewContainer: ModelAndViewContainer, nativeWebRequest: NativeWebRequest): Unit = {
    var result:ResultMessage = null
    o match {
      case o:ResultMessage => result = o.asInstanceOf[ResultMessage]
      case o:Page[Any] => {
        val p = o.asInstanceOf[Page[Any]]
        result = ResultMessage(data = PageVo(p.getTotalPages, p.getTotalElements, p.getNumber, p.getNumberOfElements,p.getContent))
      }
      case _ => result = ResultMessage(data = o)
    }
    delegate.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest)
  }
}

case class ResultMessage(@BeanProperty code: Int = 200, @BeanProperty msg: String = "success", @BeanProperty data: Any = null) extends Serializable

case class PageVo(@BeanProperty totalPages: Int, @BeanProperty totalElements: Long, @BeanProperty currentPage: Int, @BeanProperty pageSize: Int, @BeanProperty data: Any) extends Serializable
