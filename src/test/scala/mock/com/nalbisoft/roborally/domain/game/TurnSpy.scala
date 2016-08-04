package mock.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.turn.Turn

class TurnSpy extends Turn {
  var startCalled = false

  override def start(): Unit = {
    startCalled = true
  }
}
