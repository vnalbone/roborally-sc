/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.RegisterSet
import com.nalbisoft.roborally.domain.core.card.{CardDeck, Hand, ProgramCard}
import com.nalbisoft.roborally.domain.game.{Player, ProgramCardSet}
import com.nalbisoft.roborally.domain.game.turn.{DealCardsStep, ProgramRegisterStep, TurnStepFactory}

import scala.util.Try

class StubTurnStepFactory private(val dcs: DealCardsStep, val prs: ProgramRegisterStep) extends TurnStepFactory {

  def this() {
    this(null, null)
  }

  def withNewDealCardsStep(newStep: DealCardsStep): StubTurnStepFactory = {
    new StubTurnStepFactory(dcs = newStep, prs)
  }

  def withNewProgramRegistersStep(newStep: ProgramRegisterStep): StubTurnStepFactory = {
    new StubTurnStepFactory(dcs, prs = newStep)
  }

  override def createDealCardsStep: DealCardsStep = dcs

  override def createProgramRegistersStep: ProgramRegisterStep = prs
}

class MockDealCardsStep(res: Try[Map[Player, Seq[ProgramCard]]]) extends DealCardsStep {
  override def dealCards(players: Seq[Player], deck: CardDeck): Try[Unit] = {
    res map { r => r foreach { case (player, cards) => player.acceptProgramCards(cards) } }
  }
}

class MockProgramRegistersStep(res: Try[Unit]) extends ProgramRegisterStep {
  override def programRegisters(player: Player, cards: ProgramCardSet): Try[Unit] = res
}
