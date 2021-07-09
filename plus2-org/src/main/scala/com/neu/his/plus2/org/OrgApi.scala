package com.neu.his.plus2.org

import com.neu.his.plus2.core.resultpack.GlobalException
import com.neu.his.plus2.core.util.ScalaUtil
import com.neu.his.plus2.org.entity.User
import com.neu.his.plus2.org.service.OrgService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
@Api("Org demo code Restful API")
class OrgApi extends ScalaUtil{

  @Autowired
  val orgService: OrgService = null

  @GetMapping(value = Array("/users"))
  def queryUser(): Page[User] = {
    log.info("query****************************")
    orgService.queryAll()
  }

  @GetMapping(value = Array("/terror"))
  def testError():Any ={
    throw GlobalException(1023,"This is error!")
  }

}
