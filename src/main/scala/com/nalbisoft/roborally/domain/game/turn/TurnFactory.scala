package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.FactoryFloor
import com.nalbisoft.roborally.domain.game.Player

trait TurnFactory {
  def createTurn(players: Seq[Player], floor: FactoryFloor): Turn
}

case object TurnFactoryImpl extends TurnFactory {
  override def createTurn(players: Seq[Player], floor: FactoryFloor): Turn = {
    new TurnImpl(players, floor, TurnStepFactoryImpl)
  }
}