package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.RegisterSet
import com.nalbisoft.roborally.domain.game.{Player, ProgramCardSet}

import scala.util.{Success, Try}

case object ProgramRegisterStepImpl extends ProgramRegisterStep {
  override def programRegisters(player: Player, cards: ProgramCardSet): Try[RegisterSet] = {
    val regSet = new RegisterSet

    //TODO check for locked registers
    regSet.programRegister(One, cards.card1)
    regSet.programRegister(Two, cards.card2)
    regSet.programRegister(Three, cards.card3)
    regSet.programRegister(Four, cards.card4)
    regSet.programRegister(Five, cards.card5)

    Success(regSet)
  }
}
