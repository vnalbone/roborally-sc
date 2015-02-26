package com.nalbisoft.roborally.domain

case class ProgramCard(priority: Int, typ: ProgramCardType) {
  def applyMove(robot: Robot, loc: Location): Location = {
    typ.applyMove(robot, loc)
  }
}
