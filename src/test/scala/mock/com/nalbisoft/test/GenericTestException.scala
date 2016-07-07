package mock.com.nalbisoft.test

import scala.concurrent.Future
import scala.util.Failure

case class GenericTestExceptionClass() extends Exception("This is an exception thrown for test purposes")

object GenericTestException extends GenericTestExceptionClass {
  val AsFailure = Failure(GenericTestException)
  val AsFailedFuture = Future.failed(GenericTestException)
}
