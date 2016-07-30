package mock.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.game.{GameQueue, GameQueueFactory}

class GameQueueFactoryStub(spy: GameQueue) extends GameQueueFactory {
  override def create: GameQueue = {
    spy
  }
}
