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
      val regResultTry = step.programRegisters(player, cardSet)

      regResultTry must beSuccessfulTry
      regResultTry.get mustEqual SomeRegSet
    }
  }
}
