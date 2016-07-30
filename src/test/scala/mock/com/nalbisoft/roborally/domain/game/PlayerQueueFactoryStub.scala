package mock.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.game.game.{PlayerQueue, PlayerQueueFactory}

class PlayerQueueFactoryStub(spy: PlayerQueue) extends PlayerQueueFactory {
  override def create: PlayerQueue = {
    spy
  }
}
