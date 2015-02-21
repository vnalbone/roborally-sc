package com.nalbisoft.roborally.domain

object Move1 extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newPos = dir.applyMove(pos, 1)
    (dir, newPos)
  }
}

object Move2 extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newPos = dir.applyMove(pos, 2)
    (dir, newPos)
  }
}

object Move3 extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newPos = dir.applyMove(pos, 3)
    (dir, newPos)
  }
}

object BackUp extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newPos = dir.applyMove(pos, -1)
    (dir, newPos)
  }
}

object UTurn extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newDir = dir.turnAround
    (newDir, pos)
  }
}

object RotateRight extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newDir = dir.turnRight
    (newDir, pos)
  }
}

object RotateLeft extends MovementCardType {
  override def applyMove(robot: Robot, dir: Direction, pos: Position): (Direction, Position) = {
    val newDir = dir.turnLeft
    (newDir, pos)
  }
}

sealed abstract class MovementCardType {
  def applyMove(robot: Robot, direction: Direction, position: Position): (Direction, Position)
}
