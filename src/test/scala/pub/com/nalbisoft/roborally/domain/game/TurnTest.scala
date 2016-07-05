package pub.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.RegisterNumbers
import com.nalbisoft.roborally.domain.core.card.CardDeck
import com.nalbisoft.roborally.domain.game._
import mock.com.nalbisoft.roborally.domain.MockCardDeck
import mock.com.nalbisoft.roborally.domain.TestData._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class TurnTest extends Specification {
  class TurnScope extends Scope {
    val player = SomePlayer
    val turn = new Turn(Set(player), SomeFloor)
    val cards = Seq(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateRight_Low)
    val cardSet = new ProgramCardSet(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateRight_Low)

    val deck = new MockCardDeck(cards)
    SomeFloor.addRobot(player.robot, SomeLoc)
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

  "Dealing cards" should {

    "fail if turn is not active" in new TurnScope {
      turn.dealCards(player, deck) must throwAn[TurnNotStartedException]
    }

    "fail if player is not part of the game" in new TurnScope {
      turn.start()
      turn.dealCards(SomeOtherPlayer, deck) must throwAn[InvalidPlayerException]
    }

    "fail if cards were already dealt" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)
      turn.dealCards(player, deck) must throwAn[CardsAlreadyDealtException]
    }

    "return top 5 cards from deck when robot is undamaged" in new TurnScope {
      turn.start()
      val dealtCards = turn.dealCards(player, deck)
      dealtCards mustEqual cards
    }
  }

  "Programming registers" should {
    "fail if turn is not active" in new TurnScope {
      turn.programRegisters(player, cardSet) must throwAn[TurnNotStartedException]
    }

    "fail if player is not part of the game" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)

      turn.programRegisters(SomeOtherPlayer, cardSet) must throwAn[InvalidPlayerException]
    }

    "fail if registers are programmed but cards were not dealt" in new TurnScope {
      turn.start()

      turn.programRegisters(player, cardSet) must throwAn[CardsNotDealtException]
    }

    "fail if registers were already programmed" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)

      turn.programRegisters(player, cardSet)
      turn.programRegisters(player, cardSet) must throwAn[RegistersAlreadyProgrammedException]
    }

    "have registers programmed in proper order when all goes well and no locked registers" in new TurnScope {
      turn.start()
      turn.dealCards(player, deck)

      turn.programRegisters(player, cardSet)

      val robot = player.robot

      robot.registerAt(RegisterNumbers.One) must beSome(cards(0))
      robot.registerAt(RegisterNumbers.Two) must beSome(cards(1))
      robot.registerAt(RegisterNumbers.Three) must beSome(cards(2))
      robot.registerAt(RegisterNumbers.Four) must beSome(cards(3))
      robot.registerAt(RegisterNumbers.Five) must beSome(cards(4))
    }
  }


  private def completeTurnSetup(player: Player, turn: Turn, deck: CardDeck) = {
    turn.start()
    turn.dealCards(player, deck)
    turn.programRegisters(player, SomeProgramCardSet)
    //    turn.announcePowerDown(player, true)
  }

  private def endTurn(player: Player, turn: Turn, deck: CardDeck) = {
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
