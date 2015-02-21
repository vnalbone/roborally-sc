package com.nalbisoft.roborally.domain

class FactoryFloor(startPositions: Seq[Position]) {
  private val maxRobots = startPositions.size
  private var robotPos: Map[Robot, Position] = Map.empty
  private def totalRobots: Int = robotPos.size

  /**
   * Returns true if there are any spots left to add robots
   *
   * @return
   */
  def canAddMoreRobots: Boolean = totalRobots < maxRobots

  /**
   * Add a Robot to this board, if there are still available spots
   *
   * @param robot
   */
  def addRobot(robot: Robot) = {
    if(!canAddMoreRobots) {
      throw new IllegalStateException(s"Cannot add any more robots as there is a max of $maxRobots")
    }

    val robotIndex = totalRobots
    val initialPos = startPositions(robotIndex)

    robotPos = robotPos + (robot -> initialPos)
  }

  /**
   * Move the specified Robot an amount in a particular direction.
   *
   * @param robot
   * @param amount
   * @param direction
   */
  def moveRobot(robot: Robot, amount: Int, direction: Direction) = {
    if(!robotPos.contains(robot)) {
      throw new IllegalArgumentException(s"Cannot move robot '${robot.name}' as it does not exist.")
    }
    val currPos = robotPos(robot)
    val newPos  = direction.applyMove(currPos, amount)

    robotPos = robotPos + (robot -> newPos)
  }

  /**
   * Get the current position .of a Robot.
   *
   * @param robot
   * @return
   */
  def positionOf(robot: Robot): Position = {
    if(!robotPos.contains(robot)) {
      throw new IllegalArgumentException(s"Cannot get position of robot '${robot.name}' as it does not exist.")
    }

    robotPos(robot)
  }
}