package com.neu.his.plus2.org.service

import com.neu.his.plus2.org.domain.UserDo
import com.neu.his.plus2.org.entity.User
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class OrgService {

  def queryAll(): Page[User] = {
    UserDo(User()).queryUser()
  }

  def queryOne(a: String): User = {
    UserDo({
      val u = User()
      u.account = a
      u
    }).queryOneByAccount.getOrElse(null)
  }

}
