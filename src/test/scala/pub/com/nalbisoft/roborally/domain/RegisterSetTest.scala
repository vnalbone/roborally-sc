/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.{RegisterNumbers, RegisterNumber, RegisterSet}
import mock.com.nalbisoft.roborally.domain.TestData._
import org.specs2.mutable.Specification

class RegisterSetTest extends Specification {
  "Programming a register" should {

    "set the program for card for each register" in {
      val regSet = new RegisterSet()

      RegisterNumbers.all foreach { regNum =>
        testRegister(regSet, regNum)
      }
      success
    }

    def testRegister(regSet: RegisterSet, regNum: RegisterNumber) = {
      regSet.isProgrammed(regNum) must beFalse
      regSet.programmedCard(regNum) must beNone

      regSet.programRegister(regNum, SomeMCard)

      regSet.isProgrammed(regNum) must beTrue
      regSet.programmedCard(regNum) must beSome(SomeMCard)
    }

  }
}
