package com.nalbisoft.util

import scala.util.{Failure, Success, Try}

package object enrichers {
  private val Suc: Try[Unit] = Success(())

  implicit class EnrichedBoolean(b: Boolean) {
    def toTry(exOnNone: Throwable): Try[Unit] = {
      b match {
        case true => Suc
        case false => Failure(exOnNone)
      }
    }
  }

}
