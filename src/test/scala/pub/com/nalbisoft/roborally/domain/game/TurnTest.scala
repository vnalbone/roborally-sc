package pub.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.{Player, Turn}

import mock.com.nalbisoft.roborally.domain.TestData._

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

class TurnTest extends Specification {
  class TurnScope extends Scope {
    val player = SomePlayer
    val turn = new Turn(Set(player), SomeFloor)
    SomeFloor.addRobot(player.robot, SomeLoc)
  }

  "The order of the steps" should {

    "be dealt dealt before programming registers" in new TurnScope {
      turn.start()
      turn.programRegisters(player, SomeProgramCardSet) must throwAn[IllegalStateException]
    }

    "all registers should be programmed before any player can announce power downs" in new TurnScope {
      turn.start()
      turn.dealCards(player)
      turn.announcePowerDown(player, true) must throwAn[IllegalStateException]
    }

    "all power downs should be announced before completing any registers" in new TurnScope {
      turn.start()
      turn.dealCards(player)
      turn.programRegisters(player, SomeProgramCardSet)
      turn.completeRegister()  must throwAn[IllegalStateException]
    }

    "cannot end turn after one register is complete" in new TurnScope {
      completeTurnSetup(player, turn)

      turn.completeRegister()

      turn.end() must throwAn[IllegalStateException]
    }

    "cannot end turn after two registers are completed" in new TurnScope {
      completeTurnSetup(player, turn)

      turn.completeRegister()
      turn.completeRegister()

      turn.end() must throwAn[IllegalStateException]
    }

    "cannot end turn after three registers are completed" in new TurnScope {
      completeTurnSetup(player, turn)

      turn.completeRegister()
      turn.completeRegister()
      turn.completeRegister()

      turn.end() must throwAn[IllegalStateException]
    }

    "cannot end turn after four registers are completed" in new TurnScope {
      completeTurnSetup(player, turn)

      turn.completeRegister()
      turn.completeRegister()
      turn.completeRegister()
      turn.completeRegister()

      turn.end() must throwAn[IllegalStateException]
    }

    "can only end turn after five registers are completed" in new TurnScope {
      completeTurnSetup(player, turn)

      turn.completeRegister()
      turn.completeRegister()
      turn.completeRegister()
      turn.completeRegister()
      turn.completeRegister()

      turn.end()
    }
  }

  "Before the a turn starts" should {

    "announcing power down should fail" in new TurnScope {
      turn.announcePowerDown(player, true) must throwAn[IllegalStateException]
    }

    "dealing cards should fail" in new TurnScope {
      turn.dealCards(player) must throwAn[IllegalStateException]
    }

    "completing registers should fail" in new TurnScope {
      turn.completeRegister() must throwAn[IllegalStateException]
    }

    "programming registers should fail" in new TurnScope {
      turn.programRegisters(player, SomeProgramCardSet) must throwAn[IllegalStateException]
    }
  }

  "A player" should {

    "not be allowed to get dealt cards twice" in new TurnScope {
      turn.start()
      turn.dealCards(player)
      turn.dealCards(player) must throwAn[IllegalStateException]
    }

    "not be allowed to program registers twice" in new TurnScope {
      turn.start()
      turn.dealCards(player)
      turn.programRegisters(player, SomeProgramCardSet)
      turn.programRegisters(player, SomeProgramCardSet) must throwAn[IllegalStateException]
    }

    "not be allowed to announce power down twice" in new TurnScope {
      turn.start()
      turn.dealCards(player)
      turn.programRegisters(player, SomeProgramCardSet)
      turn.announcePowerDown(player, true)
      turn.announcePowerDown(player, true) must throwAn[IllegalStateException]
    }
  }

  "When a player is not part of the game" should {

    "they cannot be dealt cards" in new TurnScope {
      turn.start()
      turn.dealCards(SomeOtherPlayer) must throwAn[IllegalArgumentException]
    }

    "they cannot program registers" in new TurnScope {
      turn.start()
      turn.programRegisters(SomeOtherPlayer, SomeProgramCardSet) must throwAn[IllegalArgumentException]
    }

    "they cannot announce power down" in new TurnScope {
      turn.start()
      turn.announcePowerDown(SomeOtherPlayer, true) must throwAn[IllegalArgumentException]
    }
  }

  "After a game ends player" should {

    "no longer be dealt cards" in new TurnScope {
      endTurn(player, turn)
      turn.dealCards(SomeOtherPlayer) must throwAn[IllegalStateException]
    }
    "no longer program registers" in new TurnScope {
      endTurn(player, turn)
      turn.programRegisters(SomeOtherPlayer, SomeProgramCardSet) must throwAn[IllegalStateException]
    }
    "no longer announce power down" in new TurnScope {
      endTurn(player, turn)
      turn.announcePowerDown(SomeOtherPlayer, true) must throwAn[IllegalStateException]
    }
    "no longer complete registers" in new TurnScope {
      endTurn(player, turn)
      turn.completeRegister() must throwAn[IllegalStateException]
    }
  }

  private def completeTurnSetup(player: Player, turn: Turn) = {
    turn.start()
    turn.dealCards(player)
    turn.programRegisters(player, SomeProgramCardSet)
    turn.announcePowerDown(player, true)
  }

  private def endTurn(player: Player, turn: Turn) = {
    turn.start()
    turn.dealCards(player)
    turn.programRegisters(player, SomeProgramCardSet)
    turn.announcePowerDown(player, true)

    turn.completeRegister()
    turn.completeRegister()
    turn.completeRegister()
    turn.completeRegister()
    turn.completeRegister()

    turn.end()
  }
}
