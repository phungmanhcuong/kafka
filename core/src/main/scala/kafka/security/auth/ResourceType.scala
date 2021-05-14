/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.security.auth

import kafka.common.{BaseEnum, KafkaException}
import org.apache.kafka.common.protocol.Errors
import org.apache.kafka.common.resource.{ResourceType => JResourceType}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
sealed trait ResourceType extends BaseEnum with Ordered[ ResourceType ] {
  def error: Errors
  def toJava: JResourceType
  // this method output will not include "All" Operation type
  def supportedOperations: Set[Operation]

  override def compare(that: ResourceType): Int = this.getName compare that.getName
}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
case object Topic extends ResourceType {
  val name = "Topic"
  val error = Errors.TOPIC_AUTHORIZATION_FAILED
  val toJava = JResourceType.TOPIC
  val supportedOperations = Set(Read, Write, Create, Describe, Delete, Alter, DescribeConfigs, AlterConfigs)

  override def getName: String = name
}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
case object Group extends ResourceType {
  val name = "Group"
  val error = Errors.GROUP_AUTHORIZATION_FAILED
  val toJava = JResourceType.GROUP
  val supportedOperations = Set(Read, Describe, Delete)

  override def getName: String = name
}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
case object Cluster extends ResourceType {
  val name = "Cluster"
  val error = Errors.CLUSTER_AUTHORIZATION_FAILED
  val toJava = JResourceType.CLUSTER
  val supportedOperations = Set(Create, ClusterAction, DescribeConfigs, AlterConfigs, IdempotentWrite, Alter, Describe)

  override def getName: String = name
}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
case object TransactionalId extends ResourceType {
  val name = "TransactionalId"
  val error = Errors.TRANSACTIONAL_ID_AUTHORIZATION_FAILED
  val toJava = JResourceType.TRANSACTIONAL_ID
  val supportedOperations = Set(Describe, Write)

  override def getName: String = name
}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
case object DelegationToken extends ResourceType {
  val name = "DelegationToken"
  val error = Errors.DELEGATION_TOKEN_AUTHORIZATION_FAILED
  val toJava = JResourceType.DELEGATION_TOKEN
  val supportedOperations : Set[Operation] = Set(Describe)

  override def getName: String = name
}

@deprecated("Use org.apache.kafka.common.resource.ResourceType", "Since 2.5")
object ResourceType {

  def fromString(resourceType: String): ResourceType = {
    val rType = values.find(rType => rType.getName.equalsIgnoreCase(resourceType))
    rType.getOrElse(throw new KafkaException(resourceType + " not a valid resourceType name. The valid names are " + values.mkString(",")))
  }

  def fromJava(resourceType: JResourceType): ResourceType = {
    resourceType match {
      case JResourceType.TOPIC => Topic
      case JResourceType.GROUP => Group
      case JResourceType.CLUSTER => Cluster
      case JResourceType.TRANSACTIONAL_ID => TransactionalId
      case JResourceType.DELEGATION_TOKEN => DelegationToken
      case _ => throw new KafkaException(resourceType + " is not a convertible resource type. The valid types are " + values.mkString(","))
    }
  }

  def values: Seq[ResourceType] = List(Topic, Group, Cluster, TransactionalId, DelegationToken)
}
