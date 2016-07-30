package com.nalbisoft.roborally.domain.game.game

import com.nalbisoft.roborally.domain.game.{GameException, Player}

import scala.util.{Failure, Success, Try}

object Game {
  val MIN_PLAYERS: Int = 2
  val MAX_PLAYERS: Int = 8
}


class Game(val queueFactory: GameQueueFactory) {
  var gameStarted: Boolean = false
  var players: Seq[Player] = Nil

  val queue = queueFactory.create

  def start: Try[Unit] = {
    if (gameStarted) {
      return Failure(GameAlreadyStartedException())
    }

    if (players.size < Game.MIN_PLAYERS) {
      return Failure(NotEnoughPlayersException(players.size))
    }

    gameStarted = true

    queue.gameStarted()

    Success(())
  }

  def addPlayer(player: Player): Try[Unit] = {
    if (gameStarted) {
      return Failure(GameAlreadyStartedException())
    }

    if (players.size >= Game.MAX_PLAYERS) {
      return Failure(TooManyPlayersException())
    }

    if (players.exists(_.id == player.id)) {
      return Failure(DuplicatePlayerException(player))
    }

    players = players :+ player

    queue.playerAdded(player)

    Success(())
  }

  def
}

case class NotEnoughPlayersException(num: Int) extends GameException(s"A game must have at least 2 players.  Currently there are only $num.")
case class TooManyPlayersException() extends GameException(s"Too many players. A game can only have up to 8 players.")
case class GameAlreadyStartedException() extends GameException("Cannot add players after a game has already been started.")
case class DuplicatePlayerException(player: Player) extends GameException(s"Player ${player.name} with ID ${player.id} is already in the game.")
