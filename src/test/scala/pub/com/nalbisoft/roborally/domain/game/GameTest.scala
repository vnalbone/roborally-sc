package pub.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.{Player, PlayerId}
import com.nalbisoft.roborally.domain.game.turn._
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.test.BaseSpecs2Test

class GameTest extends BaseSpecs2Test {
  "Adding a new player to a game" should {
    "fail when game is already started" in {
      val game: Game = newValidGame

      game.start

      game.addPlayer(YetAnotherPlayer).rethrow must throwA[GameAlreadyStartedException]
    }

    "fail when there are already 8 players" in {
      val game = new Game()
      game.addPlayer(newPlayer(1))
      game.addPlayer(newPlayer(2))
      game.addPlayer(newPlayer(3))
      game.addPlayer(newPlayer(4))
      game.addPlayer(newPlayer(5))
      game.addPlayer(newPlayer(6))
      game.addPlayer(newPlayer(7))
      game.addPlayer(newPlayer(8))

      game.addPlayer(newPlayer(9)).rethrow must throwAn[TooManyPlayersException]
    }

    "fail if the same player is added twice" in {
      val game = new Game()
      game.addPlayer(SomePlayer)

      game.addPlayer(SomePlayer).rethrow must throwA[DuplicatePlayerException]
    }

    "add new players when game is not yet started" in {
      val game = new Game()

      testAddedPlayer(game, 1)
      testAddedPlayer(game, 2)
      testAddedPlayer(game, 3)
      testAddedPlayer(game, 4)
      testAddedPlayer(game, 5)
      testAddedPlayer(game, 6)
      testAddedPlayer(game, 7)
      testAddedPlayer(game, 8)
    }

    def newPlayer(num: Int) = {
      Player(PlayerId(num.toString), num.toString)
    }

    def testAddedPlayer(game: Game, num: Int) = {
      game.addPlayer(Player(PlayerId(num.toString), num.toString)) must beSuccessfulTry

      game.players must haveSize(num)
    }
  }

  "Starting a game" should {
    "fail if the game is already started" in {
      val game: Game = newValidGame

      game.start must beSuccessfulTry
      game.start.rethrow must throwA[GameAlreadyStartedException]
    }

    "fail when there is not at least 2 players" in {
      var game = new Game()
      game.start
      game.start.rethrow must throwA[NotEnoughPlayersException]

      game = new Game()
      game.addPlayer(SomePlayer)
      game.start.rethrow must throwA[NotEnoughPlayersException]
    }

    "complete successfully and have gameStarted == true" in {
      var game = new Game()
      game.addPlayer(SomePlayer)
      game.addPlayer(SomeOtherPlayer)

      game.start must beSuccessfulTry
      game.gameStarted must beTrue
    }
  }

  def newValidGame: Game = {
    val game = new Game()
    game.addPlayer(SomePlayer)
    game.addPlayer(SomeOtherPlayer)
    game
  }
}
