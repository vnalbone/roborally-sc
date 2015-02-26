package com.nalbisoft.roborally.domain

object Move1 extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 1)
    loc.copy(pos = newPos)
  }
}

object Move2 extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 2)
    loc.copy(pos = newPos)
  }
}

object Move3 extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 3)
    loc.copy(pos = newPos)
  }
}

object BackUp extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, -1)
    loc.copy(pos = newPos)
  }
}

object UTurn extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnAround
    loc.copy(dir = newDir)
  }
}

object RotateRight extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnRight
    loc.copy(dir = newDir)
  }
}

object RotateLeft extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnLeft
    loc.copy(dir = newDir)
  }
}

sealed abstract class ProgramCardType {
  def applyMove(robot: Robot, loc: Location): Location
}
