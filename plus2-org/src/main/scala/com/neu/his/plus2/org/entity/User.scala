package com.neu.his.plus2.org.entity

import java.time.LocalDateTime

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.{Entity, GeneratedValue, GenerationType, Id, Table}
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

import scala.beans.BeanProperty

@Entity
@Table
case class User() {
  @BeanProperty @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = _
  @BeanProperty var name: String = _
  @BeanProperty var account: String = _
  @BeanProperty @JsonIgnore var password: String = _
  @BeanProperty var sex: Int = _
  @BeanProperty var age: Int = _
  @BeanProperty var tel: String = _
  @BeanProperty var createTime: LocalDateTime = _
  @BeanProperty var roleId: Long = _
}

@Repository
trait UserRepository extends PagingAndSortingRepository[User,Long] {

  @Cacheable(value = Array("user"), key = "#a", sync = true)
  def findByAccount(a:String): Option[User]
}
