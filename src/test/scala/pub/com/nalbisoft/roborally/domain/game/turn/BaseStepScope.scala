package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.RegisterSet
import com.nalbisoft.roborally.domain.game.ProgramCardSet
import com.nalbisoft.roborally.domain.game.turn.{Turn, TurnStepFactoryImpl}
import mock.com.nalbisoft.roborally.domain.MockCardDeck
import mock.com.nalbisoft.roborally.domain.TestData._
import org.specs2.specification.Scope

class BaseStepScope extends Scope {
  val player = SomePlayer
  val cards = Seq(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateRight_Low)
  val cardSet = new ProgramCardSet(Move1_Low, Move2_Low, Move3_Low, UTurn_Low, RotateRight_Low)

  val deck = new MockCardDeck(cards)
  SomeFloor.addRobot(player.robot, SomeLoc)
}
