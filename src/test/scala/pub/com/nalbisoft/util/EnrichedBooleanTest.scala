package pub.com.nalbisoft.util

import mock.com.nalbisoft.test.{BaseSpecs2Test, GenericTestException, GenericTestExceptionClass}
import com.nalbisoft.util.enrichers.EnrichedBoolean

class EnrichedBooleanTest extends BaseSpecs2Test {

  "Converting boolean to try" should {
    "return a Failure when false" in {
      false.toTry(GenericTestException).rethrow must throwAn[GenericTestExceptionClass]
    }

    "return a Success when true" in {
      true.toTry(GenericTestException) must beSuccessfulTry()
    }
  }
}
