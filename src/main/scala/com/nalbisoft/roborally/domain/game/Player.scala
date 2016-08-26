package com.nalbisoft.roborally.domain.game

case class PlayerId(id: String)

case class PlayerImpl(id: PlayerId, name: String) extends Player

trait Player {
  val id: PlayerId
  val name: String
}
