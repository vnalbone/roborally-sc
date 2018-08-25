/*
 * Copyright (c) Vincent Nalbone 2018
 */

package com.nalbisoft.roborally.domain

trait FactoryFloor {
  /**
   * Returns true if there are any spots left to add robots
   *
   * @return
   */
  def canAddMoreRobots: Boolean

  /**
   * Add a Robot to this board, if there are still available spots
   *
   * @param robot
   */
  def addRobot(robot: Robot, startPos: Location)

  /**
   * Move the specified Robot an amount in a particular direction.
   *
   * @param robot
   * @param newLoc
   */
  def moveRobot(robot: Robot, newLoc: Location)

  /**
   * Get the current Location .of a Robot.
   *
   * @param robot
   * @return
   */
  def locationOf(robot: Robot): Location

  /**
    * Returns true if floor contains robot
    *
    * @param robot
    * @return
    */
  def contains(robot: Robot): Boolean
}

class BasicFactoryFloor(val maxRobots: Int) extends FactoryFloor {

  private var robotLoc: Map[Robot, Location] = Map.empty
  private def totalRobots: Int = robotLoc.size

  override def canAddMoreRobots: Boolean = totalRobots < maxRobots

  override def addRobot(robot: Robot, startPos: Location) = {
    if(!canAddMoreRobots) {
      throw new IllegalStateException(s"Cannot add any more robots as there is a max of $maxRobots")
    }

    val robotIndex = totalRobots
    robotLoc = robotLoc + (robot -> startPos)
  }

  override def moveRobot(robot: Robot, newLoc: Location) = {
    if(!robotLoc.contains(robot)) {
      throw new IllegalArgumentException(s"Cannot move robot '${robot.name}' as it does not exist.")
    }

    robotLoc = robotLoc + (robot -> newLoc)
  }

  override def locationOf(robot: Robot): Location = {
    if(!robotLoc.contains(robot)) {
      throw new IllegalArgumentException(s"Cannot get Location of robot '${robot.name}' as it does not exist.")
    }

    robotLoc(robot)
  }

  override def contains(robot: Robot): Boolean = {
    robotLoc.contains(robot)
  }
}