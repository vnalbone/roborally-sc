/*
 * Copyright (c) Vincent Nalbone 2016
 */

package pub.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.turn.{TurnFactory, TurnFactoryImpl}
import com.nalbisoft.roborally.domain.game._
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.roborally.domain.game.TurnSpy
import mock.com.nalbisoft.test.BaseSpecs2Test
import pub.com.nalbisoft.roborally.domain.game.turn.StubTurnFactory

class GameTest extends BaseSpecs2Test {

  class NewGameScope extends Scope {
    val game = new Game(SomeFloor, TurnFactoryImpl)
  }

  class ValidGameScope extends Scope {
    val player1 = SomePlayer
    val player2 = SomeOtherPlayer

    val game = newValidGame(player1, player2, TurnFactoryImpl)
  }

  "Adding a new player to a game" should {
    "fail when game is already started" in new ValidGameScope {
      game.start

      game.addPlayer(YetAnotherPlayer).assertFail[GameAlreadyStartedException]
    }

    "fail when there are already 8 players" in new NewGameScope {
      game.addPlayer(newPlayer(1))
      game.addPlayer(newPlayer(2))
      game.addPlayer(newPlayer(3))
      game.addPlayer(newPlayer(4))
      game.addPlayer(newPlayer(5))
      game.addPlayer(newPlayer(6))
      game.addPlayer(newPlayer(7))
      game.addPlayer(newPlayer(8))

      game.addPlayer(newPlayer(9)).assertFail[TooManyPlayersException]
    }

    "fail if the same player is added twice" in new NewGameScope {
      game.addPlayer(SomePlayer)

      game.addPlayer(SomePlayer).assertFail[DuplicatePlayerException]
    }

    "add new players when game is not yet started" in new NewGameScope {
      testAddedPlayer(game, 1)
      testAddedPlayer(game, 2)
      testAddedPlayer(game, 3)
      testAddedPlayer(game, 4)
      testAddedPlayer(game, 5)
      testAddedPlayer(game, 6)
      testAddedPlayer(game, 7)
      testAddedPlayer(game, 8)
    }

    def newPlayer(num: Int): PlayerImpl = {
      PlayerImpl(PlayerId(num.toString), num.toString)
    }

    def testAddedPlayer(game: Game, num: Int) = {
      val player = PlayerImpl(PlayerId(num.toString), num.toString)
      game.addPlayer(player) must beSuccessfulTry

      game.players must haveSize(num)
    }
  }

  "Starting a game" should {
    "fail if the game is already started" in new ValidGameScope {
      game.start must beSuccessfulTry
      game.start.assertFail[GameAlreadyStartedException]
    }

    "fail when there is not at least 2 players" in new NewGameScope {
      game.start
      game.start.assertFail[NotEnoughPlayersException]

      game.addPlayer(SomePlayer)
      game.start.assertFail[NotEnoughPlayersException]
    }

    "complete successfully and have gameStarted == true" in new ValidGameScope {
      game.start must beSuccessfulTry
      game.gameStarted must beTrue
    }
  }

  "Starting a new turn" should {
    "fail if the game isn't started yet" in new ValidGameScope {
      game.startNewTurn().assertFail[GameNotStartedException]
    }

    "call start on new turn if it succeeds" in {
      val turnSpy = new TurnSpy()
      val tf = new StubTurnFactory(turnSpy)

      val game = newValidGame(SomePlayer, SomeOtherPlayer, tf)

      game.start
      game.startNewTurn()

      game.currentTurn must beSome(turnSpy)
      game.turns must haveSize(1)
      game.turns(0) mustEqual turnSpy

      turnSpy.startCalled must beTrue
    }
  }

  private def newValidGame(player1: Player, player2: Player, turnFactory: TurnFactory) = {
    val game = new Game(SomeFloor, turnFactory)

    game.addPlayer(player1)
    game.addPlayer(player2)

    game
  }
}
