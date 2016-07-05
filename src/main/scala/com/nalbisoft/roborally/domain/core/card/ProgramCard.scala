package com.nalbisoft.roborally.domain.core.card

import com.nalbisoft.roborally.domain.{Location, Robot}

case class ProgramCard(priority: Int, typ: ProgramCardType) {
  def applyMove(robot: Robot, loc: Location): Location = {
    typ.applyMove(robot, loc)
  }
}
