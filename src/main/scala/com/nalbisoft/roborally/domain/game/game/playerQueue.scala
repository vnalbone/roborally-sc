package com.nalbisoft.roborally.domain.game.game

import com.nalbisoft.roborally.domain.game.Player

trait PlayerQueueFactory {
  def create: PlayerQueue
}

trait PlayerQueue {
  def playerAdded(player: Player)

  def gameStarted()
}
