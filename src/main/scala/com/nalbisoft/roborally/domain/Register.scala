package com.nalbisoft.roborally.domain

case class Register(progCard: MovementCard) {
  def applyMove(robot: Robot, loc: Location): Location = {
    progCard.applyMove(robot, loc)
  }
}
