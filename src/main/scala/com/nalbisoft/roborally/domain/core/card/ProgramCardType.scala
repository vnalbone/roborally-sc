package com.nalbisoft.roborally.domain.core.card

import com.nalbisoft.roborally.domain.{Location, Robot}

object Move1 extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 1)
    loc.copy(pos = newPos)
  }

  override def toString: String = "Move1"
}

object Move2 extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 2)
    loc.copy(pos = newPos)
  }

  override def toString: String = "Move2"
}

object Move3 extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, 3)
    loc.copy(pos = newPos)
  }

  override def toString: String = "Move3"
}

object BackUp extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newPos = loc.dir.applyMove(loc.pos, -1)
    loc.copy(pos = newPos)
  }

  override def toString: String = "BackUp"
}

object UTurn extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnAround
    loc.copy(dir = newDir)
  }

  override def toString: String = "UTurn"
}

object RotateRight extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnRight
    loc.copy(dir = newDir)
  }

  override def toString: String = "RotateRight"
}

object RotateLeft extends ProgramCardType {
  override def applyMove(robot: Robot, loc: Location): Location = {
    val newDir = loc.dir.turnLeft
    loc.copy(dir = newDir)
  }

  override def toString: String = "RotateLeft"
}

sealed abstract class ProgramCardType {
  def applyMove(robot: Robot, loc: Location): Location
}
