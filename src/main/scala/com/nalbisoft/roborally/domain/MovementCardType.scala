package com.nalbisoft.roborally.domain

object Move1 extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 1)
    loc.copy(pos = newPos)
  }
}

object Move2 extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 2)
    loc.copy(pos = newPos)
  }
}

object Move3 extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 3)
    loc.copy(pos = newPos)
  }
}

object BackUp extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, -1)
    loc.copy(pos = newPos)
  }
}

object UTurn extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnAround
    loc.copy(dir = newDir)
  }
}

object RotateRight extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnRight
    loc.copy(dir = newDir)
  }
}

object RotateLeft extends MovementCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnLeft
    loc.copy(dir = newDir)
  }
}

sealed abstract class MovementCardType {
  def applyMove(robot: Robot, loc: Location): Location
}
