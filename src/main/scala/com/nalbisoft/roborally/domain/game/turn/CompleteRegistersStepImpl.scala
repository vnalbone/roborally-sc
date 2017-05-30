/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.{FactoryFloor, RegisterNumber}
import com.nalbisoft.roborally.domain.game.{GameException, Player, PlayerCard}

import scala.util.{Failure, Success, Try}


object CompleteRegistersStepImpl extends CompleteRegistersStep {
  override def completeRegisters(players: Seq[Player],
                                 register: RegisterNumber,
                                 floor: FactoryFloor): Try[Unit] = {

    def allPlayersProgrammed(): Boolean = {
      players.map(p =>
        p.register.isDefined && p.register.get.isProgrammed(register)
      ).forall(p => p)
    }

    if (!allPlayersProgrammed()) {
      return Failure(new RegistersNotProgrammedException())
    }

    var cardmap = players.map { p =>
      val card = p.register.get.programmedCard(register)
      (p, PlayerCard(p.id, register, card.get))
    }.toMap

    doReveal(players, cardmap.values.toSeq)

    Success(())
  }

  private def doReveal(players: Seq[Player], cards: Seq[PlayerCard]): Unit = {
    players foreach { p =>
      p.revealCards(cards)
    }
  }
}

class RegistersNotProgrammedException() extends GameException("All players must have their registers programmed")
