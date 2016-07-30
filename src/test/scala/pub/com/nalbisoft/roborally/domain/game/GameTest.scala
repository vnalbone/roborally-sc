package pub.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.game._
import com.nalbisoft.roborally.domain.game.{Player, PlayerId}
import mock.com.nalbisoft.roborally.domain.TestData._
import mock.com.nalbisoft.roborally.domain.game.{PlayerQueueFactoryStub, PlayerQueueSpy}
import mock.com.nalbisoft.test.BaseSpecs2Test





class GameTest extends BaseSpecs2Test {

  class NewGameScope extends Scope {
    val queueSpy = new PlayerQueueSpy()
    val game = new Game(new PlayerQueueFactoryStub(queueSpy))
  }

  class ValidGameScope extends Scope {
    val player1 = SomePlayer
    val player2 = SomeOtherPlayer

    val queueSpy = new PlayerQueueSpy()
    val game = new Game(new PlayerQueueFactoryStub(queueSpy))

    game.addPlayer(player1)
    game.addPlayer(player2)
  }

  "Adding a new player to a game" should {
    "fail when game is already started" in new ValidGameScope {
      game.start

      game.addPlayer(YetAnotherPlayer).rethrow must throwA[GameAlreadyStartedException]
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

      game.addPlayer(newPlayer(9)).rethrow must throwAn[TooManyPlayersException]
    }

    "fail if the same player is added twice" in new NewGameScope {
      game.addPlayer(SomePlayer)

      game.addPlayer(SomePlayer).rethrow must throwA[DuplicatePlayerException]
    }

    "add new players when game is not yet started" in new NewGameScope {
      testAddedPlayer(game, 1, queueSpy)
      testAddedPlayer(game, 2, queueSpy)
      testAddedPlayer(game, 3, queueSpy)
      testAddedPlayer(game, 4, queueSpy)
      testAddedPlayer(game, 5, queueSpy)
      testAddedPlayer(game, 6, queueSpy)
      testAddedPlayer(game, 7, queueSpy)
      testAddedPlayer(game, 8, queueSpy)
    }

    def newPlayer(num: Int) = {
      Player(PlayerId(num.toString), num.toString)
    }

    def testAddedPlayer(game: Game, num: Int, spy: PlayerQueueSpy) = {
      val player = Player(PlayerId(num.toString), num.toString)
      game.addPlayer(player) must beSuccessfulTry

      game.players must haveSize(num)

      spy.addedPlayers must haveSize(num)
      spy.addedPlayers(num - 1) mustEqual player
    }
  }

  "Starting a game" should {
    "fail if the game is already started" in new ValidGameScope {
      game.start must beSuccessfulTry
      game.start.rethrow must throwA[GameAlreadyStartedException]
    }

    "fail when there is not at least 2 players" in new NewGameScope {
      game.start
      game.start.rethrow must throwA[NotEnoughPlayersException]

      game.addPlayer(SomePlayer)
      game.start.rethrow must throwA[NotEnoughPlayersException]
    }

    "complete successfully and have gameStarted == true" in new ValidGameScope {
      game.start must beSuccessfulTry
      game.gameStarted must beTrue

      queueSpy.gameStartedCalled must beTrue
    }
  }
}
