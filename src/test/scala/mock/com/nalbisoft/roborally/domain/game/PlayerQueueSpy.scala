package mock.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.Player
import com.nalbisoft.roborally.domain.game.game.PlayerQueue

class PlayerQueueSpy extends PlayerQueue {
  var addedPlayers: Seq[Player] = Nil

  override def playerAdded(player: Player): Unit = {
    addedPlayers = addedPlayers :+ player
  }

  var gameStartedCalled: Boolean = false

  override def gameStarted(): Unit = {
    gameStartedCalled = true
  }
}
