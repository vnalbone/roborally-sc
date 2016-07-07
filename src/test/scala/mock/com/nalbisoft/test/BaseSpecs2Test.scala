package mock.com.nalbisoft.test

import org.specs2.matcher.{Expectable, MatchResult, Matcher}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.duration._
import scala.reflect.ClassTag
import scala.util.Try

class BaseSpecs2Test extends org.specs2.mutable.Specification {
  type Scope = org.specs2.specification.Scope

  /**
    * Example:
    * apiShare must haveFieldCount(11)
    *
    * @param n number of fields
    * @return Matcher used with specs2 must
    */
  def haveFieldCount(n: Int) = new Matcher[Product] {
    def apply[P <: Product](p: Expectable[P]) =
      result(
        p.value.productArity == n,
        p.description + s" has $n fields",
        p.description + s" has ${p.value.productArity} fields, not $n !!",
        p
      )
  }

  /**
    * Example:
    * apiShares mustHave (_.id) matching domainShares
    *
    * @param  iter the API objects
    * @tparam T the API object type
    */
  implicit class MustHaveIterableEnricher[T](val iter: Iterable[T]) {
    val seq = iter.toSeq

    // so that it can iterate multiple times
    def mustHave[A](f: T => A) = MustHave((seq map f).toSet)

    case class MustHave[A](left: Set[A]) {
      def matching(right: Map[A, _]): MatchResult[Any] = matching(right.keySet)

      def matching(right: Set[A]): MatchResult[Any] = {
        // Note: do not take the size of left, use the iter size instead
        seq.size mustEqual right.size
        left mustEqual right
      }
    }

  }

  def assertSuccessfulFuture(future: Future[_]) = {
    future.waitForResult //If there is a failure it would be thrown here
    "" mustEqual ""
  }

  // Support for testing reactive stuff (Futures).

  val waitTime = Duration(5, SECONDS)

  val FutureTrue = Future.successful(true)
  val FutureFalse = Future.successful(false)
  val FutureSuccess = Future.successful(())

  /**
    * Allows you to check that a Future resulted in an exception, e.g.
    * val futureThrowable = someFuture.failed
    * lazy val result = Await.result(futureThrowable, waitTime)
    * result.rethrow must throwAn[ExecutionException]
    */
  implicit class Rethrow(t: Throwable) {
    def rethrow: Throwable = throw t
  }

  implicit class RethrowTry[T](t: Try[T]) {
    def rethrow: Throwable = {
      t must beFailedTry
      throw t.failed.get
    }

    def assertFail[E <: Throwable](implicit m: ClassTag[E]) = {
      t must beFailedTry
      t.rethrow must throwA[E]
    }

    def extractSuccess: T = {
      t must beSuccessfulTry
      t.get
    }
  }

  implicit class TestEnrichedFuture[T](t: Future[T]) {
    def waitForResult: T = Await.result(t, waitTime)
  }

  implicit class TestEnrichedOption[T](t: Option[T]) {
    def extractSome: T = {
      t must beSome
      t.get
    }
  }

}
