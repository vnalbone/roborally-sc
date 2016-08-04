package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.FactoryFloor
import com.nalbisoft.roborally.domain.game.Player
import com.nalbisoft.roborally.domain.game.turn.{Turn, TurnFactory}

class StubTurnFactory(turnToUse: Turn) extends TurnFactory {
  override def createTurn(players: Seq[Player], floor: FactoryFloor): Turn = {
    turnToUse
  }
}
