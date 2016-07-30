package com.nalbisoft.roborally.domain.game.game.turn

import com.nalbisoft.roborally.domain.RegisterSet
import com.nalbisoft.roborally.domain.game.{Player, ProgramCardSet}

import scala.util.Try

trait ProgramRegisterStep {
  def programRegisters(player: Player, cards: ProgramCardSet): Try[RegisterSet]
}
