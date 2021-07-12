package com.neu.his.plus2.org.domain

import java.time.LocalDateTime
import java.util.Optional

import com.fasterxml.jackson.annotation.JsonIgnore
import com.neu.his.plus2.core.util.ApplicationContextUtil
import com.neu.his.plus2.org.entity.{Role, RoleRepository, User, UserRepository}
import org.springframework.data.domain.{Page, PageRequest, Pageable}
import springfox.documentation.annotations.Cacheable

/**
  * @author tanchy
  */
case class UserDo(u: User = null, @JsonIgnore r: Role = null) {

  val userRepository: UserRepository = ApplicationContextUtil.getBeanByClass(classOf[UserRepository])

  val roleRepository: RoleRepository = ApplicationContextUtil.getBeanByClass(classOf[RoleRepository])

  def queryUser(body: UserDo => Page[User] = null, p: Pageable = PageRequest.of(1,10)): Page[User] = {
    Option(body) match {
      case Some(v) => body(this)
      case _ => userRepository.findAll(p)
    }
  }

  def queryOneById(): Optional[User] = userRepository.findById(u.id)

  def queryOneByAccount(): Option[User] = userRepository.findByAccount(u.account)

  def registerUser(body: UserDo => User = null): User = {
    Option(body) match {
      case Some(v) => body(this)
      case _ => {
        assert(Option(u.account).nonEmpty, "Check user entity's paramter account is empty. Please check other parameter at the same time.")
        assert(Option(u.name).nonEmpty, "Check user entity's paramter name is empty. Please check other parameter at the same time.")
        assert(Option(u.roleId).nonEmpty, "Check user entity's paramter role id is empty. Please check other parameter at the same time.")
        assert(roleRepository.existsById(u.roleId), "Check user entity's paramter role id is invalid.")
        Option(userRepository.findByAccount(u.account).get) match {
          case Some(uu) =>
            assert(1 > 1,"Check user entity is exist. Please check other parameter at the same time.")
            u
          case _ =>
            u.createTime = LocalDateTime.now
            userRepository.save(u)
        }
      }
    }
  }

  def modifyUser(body: UserDo => User = null): User = {
    Option(body) match {
      case Some(b) => b(this)
      case _ => {
        assert(Option(u.account).nonEmpty, "Check user entity's paramter account is empty. Please check other parameter at the same time.")
        assert(Option(u.name).nonEmpty, "Check user entity's paramter name is empty. Please check other parameter at the same time.")
        assert(Option(u.roleId).nonEmpty, "Check user entity's paramter role id is empty. Please check other parameter at the same time.")
        assert(roleRepository.existsById(u.roleId), "Check user entity's paramter role id is invalid.")
        userRepository.findByAccount(u.account) match {
          case Some(uu) =>
            uu.setName(u.name)
            uu.setRoleId(u.roleId)
            uu.setAge(Option(u.age).getOrElse(uu.age))
            uu.setSex(Option(u.sex).getOrElse(uu.sex))
            userRepository.save(uu)
          case _ =>
            assert(1 > 1,"Check user entity's paramter account is not exist.")
            u
        }
      }
    }
  }

  def modifyPasswordUser(oPwd: String, nPwd: String): User = {
    userRepository.findByAccount(u.account) match {
      case Some(uu) =>
        //TODO password encryption
        uu.password match {
          case oPwd => {
            uu.password = nPwd
            userRepository.save(uu)
          }
          case _ => {
            assert(1 > 1,"Check old password is not equals database password.")
            u
          }
        }
      case _ =>
        assert(1 > 1,"Check user entity's paramter account is not exist.")
        u
    }
  }

  def dropUser(u: User): User = {
    userRepository.findByAccount(u.account) match {
      case Some(uu) =>
        userRepository.deleteById(uu.id)
        uu
      case _ =>
        assert(1 > 1,"Check user entity's paramter account is not exist.")
        u
    }
  }
}
