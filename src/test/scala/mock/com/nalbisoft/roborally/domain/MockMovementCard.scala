package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain._

class MockMovementCard(priority: Int, mType: MovementCardType, locFunc: => Location) extends MovementCard(priority, mType) {
  var spiedRobot: Option[Robot] = None
  var spiedLocation: Option[Location] = None

  override def applyMove(robot: Robot, loc: Location): Location = {
    spiedRobot = Some(robot)
    spiedLocation = Some(loc)

    locFunc
  }
}
