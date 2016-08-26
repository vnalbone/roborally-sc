package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.core.card.BasicCardDeck
import com.nalbisoft.roborally.domain.game.ProgramCardSet
import mock.com.nalbisoft.roborally.domain.TestData._
import org.specs2.specification.Scope

class BaseStepScope extends Scope {
  val player = SomePlayer
  val cards = Seq(
    Move1_Low,
    Move2_Low,
    Move3_Low,
    UTurn_Low,
    RotateRight_Low,
    RotateLeft_Low,
    Move1_High,
    Move2_High,
    Move3_High,
    UTurn_High,
    RotateRight_High,
    RotateLeft_High)

  val cardSet = new ProgramCardSet(
    Move1_Low,
    Move2_Low,
    Move3_Low,
    UTurn_Low,
    RotateRight_Low
  )

  val deck = new BasicCardDeck(cards)
}
