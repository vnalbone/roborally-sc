//package pub.com.nalbisoft.roborally.domain.game.turn
//
//import com.nalbisoft.roborally.domain.RegisterNumbers
//import com.nalbisoft.roborally.domain.game.turn._
//import mock.com.nalbisoft.roborally.domain.TestData._
//
//class ProgramRegisterStepImplTest {
//  class ProgramRegisterStepScope extends BaseStepScope {
//    val step = new ProgramRegisterStepImpl()
//  }
//
//  "Programming registers" should {
//    "have registers programmed in proper order when all goes well and no locked registers" in new ProgramRegisterStepScope {
//      turn.start()
//      turn.dealCards(player, deck)
//
//      turn.programRegisters(player, cardSet)
//
//      val robot = player.robot
//
//      robot.registerAt(RegisterNumbers.One) must beSome(cards(0))
//      robot.registerAt(RegisterNumbers.Two) must beSome(cards(1))
//      robot.registerAt(RegisterNumbers.Three) must beSome(cards(2))
//      robot.registerAt(RegisterNumbers.Four) must beSome(cards(3))
//      robot.registerAt(RegisterNumbers.Five) must beSome(cards(4))
//    }
//  }
//}
