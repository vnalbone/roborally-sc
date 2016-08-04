package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.{RegisterNumbers, RegisterSet}
import com.nalbisoft.roborally.domain.core.card.CardDeck
import com.nalbisoft.roborally.domain.game._
import com.nalbisoft.roborally.domain.game.turn._
import mock.com.nalbisoft.roborally.domain.MockCardDeck
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.test.{BaseSpecs2Test, GenericTestException}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.util.{Failure, Success}

class TurnImplTest extends BaseSpecs2Test {
  class TurnScope extends Scope {

    val cards = Seq(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateRight_Low)
    val cardSet = new ProgramCardSet(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateRight_Low)
    val deck = new MockCardDeck(cards)

    val regSet = new RegisterSet()
      .programRegister(One, Move1_Low)
      .programRegister(Two, Move2_Low)
      .programRegister(Three, Move3_Low)
      .programRegister(Four, UTurn_Low)
      .programRegister(Five, RotateRight_Low)

    val player = SomePlayer
    //    SomeFloor.addRobot(player.robot, SomeLoc)

    val drawStep = new MockDealCardsStep(Success(cards))
    val progRegStep = new MockProgramRegistersStep(Success(regSet))
    val stepFactory = new StubTurnStepFactory().withNewDealCardsStep(drawStep).withNewProgramRegistersStep(progRegStep)
    val turn = new TurnImpl(Seq(player), SomeFloor, stepFactory)
  }

  //  "The order of the steps" should {
  //
  //    "be dealt dealt before programming registers" in new TurnScope {
  //      turn.start()
  //      turn.programRegisters(player, SomeProgramCardSet) must throwAn[IllegalStateException]
  //    }
  //
  //    "all registers should be programmed before any player can announce power downs" in new TurnScope {
  //      turn.start()
  //      turn.dealCards(player)
  //      turn.announcePowerDown(player, true) must throwAn[IllegalStateException]
  //    }
  //
  //    "all power downs should be announced before completing any registers" in new TurnScope {
  //      turn.start()
  //      turn.dealCards(player)
  //      turn.programRegisters(player, SomeProgramCardSet)
  //      turn.completeRegister()  must throwAn[IllegalStateException]
  //    }
  //
  //    "cannot end turn after one register is complete" in new TurnScope {
  //      completeTurnSetup(player, turn)
  //
  //      turn.completeRegister()
  //
  //      turn.end() must throwAn[IllegalStateException]
  //    }
  //
  //    "cannot end turn after two registers are completed" in new TurnScope {
  //      completeTurnSetup(player, turn)
  //
  //      turn.completeRegister()
  //      turn.completeRegister()
  //
  //      turn.end() must throwAn[IllegalStateException]
  //    }
  //
  //    "cannot end turn after three registers are completed" in new TurnScope {
  //      completeTurnSetup(player, turn)
  //
  //      turn.completeRegister()
  //      turn.completeRegister()
  //      turn.completeRegister()
  //
  //      turn.end() must throwAn[IllegalStateException]
  //    }
  //
  //    "cannot end turn after four registers are completed" in new TurnScope {
  //      completeTurnSetup(player, turn)
  //
  //      turn.completeRegister()
  //      turn.completeRegister()
  //      turn.completeRegister()
  //      turn.completeRegister()
  //
  //      turn.end() must throwAn[IllegalStateException]
  //    }
  //
  ////    "can only end turn after five registers are completed" in new TurnScope {
  ////      completeTurnSetup(player, turn)
  ////
  ////      turn.completeRegister()
  ////      turn.completeRegister()
  ////      turn.completeRegister()
  ////      turn.completeRegister()
  ////      turn.completeRegister()
  ////
  ////      turn.end()
  ////    }
  //  }

  //  "Before the a turn starts" should {
  //
  //    "announcing power down should fail" in new TurnScope {
  //      turn.announcePowerDown(player, true) must throwAn[TurnNotStartedException]
  //    }
  //
  //    "completing registers should fail" in new TurnScope {
  //      turn.completeRegister() must throwAn[TurnNotStartedException]
  //    }
  //
  //    "programming registers should fail" in new TurnScope {
  //      turn.programRegisters(player, SomeProgramCardSet) must throwAn[TurnNotStartedException]
  //    }
  //  }

  //  "A player" should {
  //
  //
  //    "not be allowed to program registers twice" in new TurnScope {
  //      turn.start()
  //      turn.dealCards(player)
  //      turn.programRegisters(player, SomeProgramCardSet)
  //      turn.programRegisters(player, SomeProgramCardSet) must throwAn[IllegalStateException]
  //    }
  //
  //    "not be allowed to announce power down twice" in new TurnScope {
  //      turn.start()
  //      turn.dealCards(player)
  //      turn.programRegisters(player, SomeProgramCardSet)
  //      turn.announcePowerDown(player, true)
  //      turn.announcePowerDown(player, true) must throwAn[IllegalStateException]
  //    }
  //  }

  //  "When a player is not part of the game" should {
  //
  //    "they cannot be dealt cards" in new TurnScope {
  //      turn.start()
  //      turn.dealCards(SomeOtherPlayer) must throwAn[IllegalArgumentException]
  //    }
  //
  //    "they cannot program registers" in new TurnScope {
  //      turn.start()
  //      turn.programRegisters(SomeOtherPlayer, SomeProgramCardSet) must throwAn[IllegalArgumentException]
  //    }
  //
  //    "they cannot announce power down" in new TurnScope {
  //      turn.start()
  //      turn.announcePowerDown(SomeOtherPlayer, true) must throwAn[IllegalArgumentException]
  //    }
  //  }

  //  "After a game ends player" should {
  //
  //    "no longer be dealt cards" in new TurnScope {
  //      endTurn(player, turn)
  //      turn.dealCards(SomeOtherPlayer) must throwAn[TurnAlreadyEndedException]
  //    }
  //    "no longer program registers" in new TurnScope {
  //      endTurn(player, turn)
  //      turn.programRegisters(SomeOtherPlayer, SomeProgramCardSet) must throwAn[TurnAlreadyEndedException]
  //    }
  //    "no longer announce power down" in new TurnScope {
  //      endTurn(player, turn)
  //      turn.announcePowerDown(SomeOtherPlayer, true) must throwAn[TurnAlreadyEndedException]
  //    }
  //    "no longer complete registers" in new TurnScope {
  //      endTurn(player, turn)
  //      turn.completeRegister() must throwAn[TurnAlreadyEndedException]
  //    }
  //  }

  "Dealing cards step" should {

    "fail if turn is not active" in new TurnScope {
      turn.dealCards(player, deck).rethrow must throwAn[TurnNotStartedException]
    }

    "fail if player is not part of the game" in new TurnScope {
      turn.start()
      turn.dealCards(SomeOtherPlayer, deck).rethrow must throwAn[InvalidPlayerException]
    }

    "fail if cards were already dealt" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)
      turn.dealCards(player, deck).rethrow must throwA[CardsAlreadyDealtException]
    }

    "remain incomplete if there is an error executing the step" in new TurnScope {
      val failedStep = new MockDealCardsStep(Failure(GenericTestException))
      val failedFactory = new StubTurnStepFactory().withNewDealCardsStep(failedStep)

      val failedTurn = new TurnImpl(Seq(player), SomeFloor, failedFactory)

      failedTurn.start()
      val dealtCards = failedTurn.dealCards(player, deck)

      dealtCards must beFailedTry(GenericTestException)
      failedTurn.isDealCardsStepCompleted(player) must beFalse
    }

    "return top 5 cards from deck when robot is undamaged" in new TurnScope {
      turn.start()
      val dealtCards = turn.dealCards(player, deck)

      dealtCards must beSuccessfulTry(cards)
      turn.isDealCardsStepCompleted(player) must beTrue
    }
  }

  "Programming registers" should {
    "fail if turn is not active" in new TurnScope {
      turn.programRegisters(player, cardSet).rethrow must throwAn[TurnNotStartedException]
    }

    "fail if player is not part of the game" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)

      turn.programRegisters(SomeOtherPlayer, cardSet).rethrow must throwAn[InvalidPlayerException]
    }

    "fail if registers are programmed but cards were not dealt" in new TurnScope {
      turn.start()

      turn.programRegisters(player, cardSet).rethrow must throwAn[CardsNotDealtException]
    }

    "fail if registers were already programmed" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)

      turn.programRegisters(player, cardSet)
      turn.programRegisters(player, cardSet).rethrow must throwAn[RegistersAlreadyProgrammedException]
    }

    "remain incomplete if there is an error executing the step" in new TurnScope {
      val failedStep = new MockProgramRegistersStep(Failure(GenericTestException))
      val failedFactory = new StubTurnStepFactory()
        .withNewDealCardsStep(new MockDealCardsStep(Success(Nil)))
        .withNewProgramRegistersStep(failedStep)

      val failedTurn = new TurnImpl(Seq(player), SomeFloor, failedFactory)

      failedTurn.start()
      val dealtCards = failedTurn.dealCards(player, deck)

      failedTurn.programRegisters(player, cardSet) must beFailedTry(GenericTestException)

      failedTurn.isProgramRegistersStepCompleted(player) must beFalse
    }

    "have registers programmed in proper order when all goes well and no locked registers" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)

      val regResultTry = turn.programRegisters(player, cardSet)

      regResultTry must beSuccessfulTry
      regResultTry.get mustEqual regSet
    }
  }

  private def completeTurnSetup(player: Player, turn: TurnImpl, deck: CardDeck) = {
    turn.start()
    turn.dealCards(player, deck)
    turn.programRegisters(player, SomeProgramCardSet)
    //    turn.announcePowerDown(player, true)
  }

  private def endTurn(player: Player, turn: TurnImpl, deck: CardDeck) = {
    turn.start()
    turn.dealCards(player, deck)
    turn.programRegisters(player, SomeProgramCardSet)
    //    turn.announcePowerDown(player, true)

    turn.completeRegister()
    turn.completeRegister()
    turn.completeRegister()
    turn.completeRegister()
    turn.completeRegister()

    turn.cleanUp()
  }
}
