/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.util

import scala.util.{Failure, Success, Try}

package object enrichers {
  private val Suc: Try[Unit] = Success(())

  implicit class EnrichedSeq[T](s: Seq[T]) {
    def skip[A](n: Int): Seq[T] = {
      if (n == 0) {
        return s
      }

      val toSkip = n + 1

      s.zipWithIndex.collect { case (e, i) if (i % toSkip) == 0 => e } // (i+1) because zipWithIndex is 0-based
    }

    def skipOther[A](n: Int): Seq[T] = {
      if (n == 0) {
        return s
      }
      val toSkip = n + 1

      s.zipWithIndex.collect { case (e, i) if ((i + 1) % toSkip) == 0 => e } // (i+1) because zipWithIndex is 0-based
    }
  }

  implicit class EnrichedBoolean(b: Boolean) {
    def toTry(exOnNone: Throwable): Try[Unit] = {
      b match {
        case true => Suc
        case false => Failure(exOnNone)
      }
    }
  }

}
