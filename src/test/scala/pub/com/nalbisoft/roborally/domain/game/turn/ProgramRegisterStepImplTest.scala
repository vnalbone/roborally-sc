/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain.game.turn

import com.nalbisoft.roborally.domain.game.turn._

import mock.com.nalbisoft.test.BaseSpecs2Test
import mock.com.nalbisoft.roborally.domain.TestData._

class ProgramRegisterStepImplTest extends BaseSpecs2Test {

  class ProgramRegisterStepScope extends BaseStepScope {
    val step = ProgramRegisterStepImpl
  }

  "Programming registers" should {
    "have registers programmed in proper order when all goes well and no locked registers" in new ProgramRegisterStepScope {
      val regResultTry = step.programRegisters(player1, cardSet)

      regResultTry must beSuccessfulTry
      player1.passedRegister must beSome(SomeRegSet)
    }
  }
}
