package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._

class MockProgramCard(priority: Int, mType: ProgramCardType, locFunc: => Location) extends ProgramCard(priority, mType) {
  var spiedRobot: Option[Robot] = None
  var spiedLocation: Option[Location] = None

  override def applyMove(robot: Robot, loc: Location): Location = {
    spiedRobot = Some(robot)
    spiedLocation = Some(loc)

    locFunc
  }
}
