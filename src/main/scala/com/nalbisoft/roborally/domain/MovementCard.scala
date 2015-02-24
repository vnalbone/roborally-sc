package com.nalbisoft.roborally.domain

case class MovementCard(priority: Int, mType: MovementCardType) {
  def applyMove(robot: Robot, loc: Location): Location = {
    mType.applyMove(robot, loc)
  }
}
