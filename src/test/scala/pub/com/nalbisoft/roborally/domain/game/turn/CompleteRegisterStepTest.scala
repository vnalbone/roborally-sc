/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.RegisterNumbers._
import com.nalbisoft.roborally.domain.game.turn.{CompleteRegistersStepImpl, RegistersNotProgrammedException}
import mock.com.nalbisoft.test.BaseSpecs2Test
import mock.com.nalbisoft.roborally.domain.TestData._

/**
  *A. Reveal Program Cards
  *B. Robots Move
  *C. Board Elements Move
  *D. Lasers Fire
  *E. Touch Checkpoints
  */
class CompleteRegisterStepTest extends BaseSpecs2Test {

  class CompleteRegisterStepScope extends BaseStepScope {

    val step = CompleteRegistersStepImpl

    val floor = SomeFloor

    val playerData = Seq(p1Data, p2Data)

    val firstPlayer = p1Data
    val secondPlayer = p2Data
    val players = playerData.map(_.player)

  }

  "Completing a register" should {
    "fail if registers have not been programmed" in new CompleteRegisterStepScope {
      step.completeRegisters(Seq(SomePlayer, SomeOtherPlayer), One, floor).assertFail[RegistersNotProgrammedException]
    }

    "cause the appropriate reveal to be called on each player" in new CompleteRegisterStepScope {
      step.completeRegisters(players, One, floor) must beSuccessfulTry

      val passedCards = firstPlayer.player.passedPlayerCards.extractSome
      passedCards must haveSize(2)

      passedCards(0).playerId mustEqual player1.id
      passedCards(0).registerNum mustEqual One
      passedCards(0).card mustEqual firstPlayer.firstCard

      passedCards(1).playerId mustEqual player2.id
      passedCards(1).registerNum mustEqual One
      passedCards(1).card mustEqual secondPlayer.firstCard
    }

    "move the board" in new CompleteRegisterStepScope {
      step.completeRegisters(players, One, floor) must beSuccessfulTry


    }
  }
}
