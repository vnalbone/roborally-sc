/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.{FactoryFloor, RegisterNumber}
import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}
import com.nalbisoft.roborally.domain.game.{Player, ProgramCardSet}
import com.nalbisoft.roborally.domain.game.turn.{CompleteRegistersStep, DealCardsStep, ProgramRegisterStep, TurnStepFactory}

import scala.util.Try

class StubTurnStepFactory private(val dcs: DealCardsStep, val prs: ProgramRegisterStep, val crs: CompleteRegistersStep) extends TurnStepFactory {

  def this() {
    this(null, null, null)
  }

  def withNewDealCardsStep(newStep: DealCardsStep): StubTurnStepFactory = {
    new StubTurnStepFactory(dcs = newStep, prs, crs)
  }

  def withNewProgramRegistersStep(newStep: ProgramRegisterStep): StubTurnStepFactory = {
    new StubTurnStepFactory(dcs, prs = newStep, crs)
  }

  def withNewCompleteRegistersStep(newStep: CompleteRegistersStep): StubTurnStepFactory = {
    new StubTurnStepFactory(dcs, prs, crs = newStep)
  }

  override def createDealCardsStep: DealCardsStep = dcs

  override def createProgramRegistersStep: ProgramRegisterStep = prs

  override def createCompleteRegistersStep: CompleteRegistersStep = crs
}

class MockDealCardsStep(res: Try[Map[Player, Seq[ProgramCard]]]) extends DealCardsStep {
  override def dealCards(players: Seq[Player], deck: CardDeck): Try[Unit] = {
    res map { r => r foreach { case (player, cards) => player.acceptProgramCards(cards) } }
  }
}

class MockProgramRegistersStep(res: Try[Unit]) extends ProgramRegisterStep {
  override def programRegisters(player: Player, cards: ProgramCardSet): Try[Unit] = res
}

class MockCompleteRegistersStep(res: Try[Unit]) extends CompleteRegistersStep {
  override def completeRegisters(player: Seq[Player], regNum: RegisterNumber, floor: FactoryFloor): Try[Unit] = res
}
