package com.nalbisoft.roborally.domain.game.game

import com.nalbisoft.roborally.domain.game.Player

trait GameQueueFactory {
  def create: GameQueue
}

trait GameQueue {
  def playerAdded(player: Player)

  def gameStarted()
}
