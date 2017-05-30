/*
 * Copyright (c) Vincent Nalbone 2017
 */

package mock.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.RegisterSet
import com.nalbisoft.roborally.domain.core.card.ProgramCard
import com.nalbisoft.roborally.domain.game.{Player, PlayerCard, PlayerId}

case class PlayerSpy(id: PlayerId,
                     name: String,
                     programCards: Option[Seq[ProgramCard]] = None,
                     register: Option[RegisterSet] = None) extends Player {
  var passedProgramCards: Option[Seq[ProgramCard]] = None
  var passedRegister: Option[RegisterSet] = None
  var passedPlayerCards: Option[Seq[PlayerCard]] = None

  def withRegister(reg: RegisterSet) = {
    PlayerSpy(id, name, programCards, Some(reg))
  }

  def withProgramCards(c: Seq[ProgramCard]) = {
    PlayerSpy(id, name, Some(c), register)
  }

  override def acceptProgramCards(cards: Seq[ProgramCard]) = {
    passedProgramCards = Some(cards)
  }

  override def acceptProgrammedRegister(register: RegisterSet): Unit = {
    passedRegister = Some(register)
  }

  override def revealCards(playerCards: Seq[PlayerCard]): Unit = {
    passedPlayerCards = Some(playerCards)
  }
}
