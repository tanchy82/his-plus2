package com.neu.his.plus2.org.entity

import javax.persistence._
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

import scala.beans.BeanProperty

@Entity
@Table
case class Role(){
  @BeanProperty @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = _
  @BeanProperty var name: String = _
}

@Repository
trait RoleRepository extends PagingAndSortingRepository[Role,Long]
