/*
 * Copyright (c) Vincent Nalbone 2017
 */

package pub.com.nalbisoft.util

import com.nalbisoft.util.enrichers.EnrichedSeq
import mock.com.nalbisoft.test.BaseSpecs2Test

class EnrichedSeqTest extends BaseSpecs2Test {

  "Calling skip on a Seq" should {
    "return an empty list if the list is empty" in {
      Seq().skip(2) must beEmpty
    }

    "return just the first element if skip number is greater than the number of elements in the list" in {
      Seq("a", "b").skip(3) mustEqual Seq("a")
    }

    "return the list if the skip number is 0" in {
      val s = Seq("a", "b")
      s.skip(0) mustEqual s
    }

    "return every other element in the list if the skip number is 1" in {
      Seq("a", "b", "c", "d").skip(1) mustEqual Seq("a", "c")
    }

    "return every other element in the list if the skip number is greater than 1" in {
      Seq("a", "b", "c", "d", "e", "f", "g", "h", "i", "j").skip(4) mustEqual Seq("a", "f")
    }
  }

  "Calling skipOther on a Seq" should {
    "return an empty list if the list is empty" in {
      Seq().skipOther(2) must beEmpty
    }

    "return an empty list if skip number is greater than the number of elements in the list" in {
      Seq("a", "b").skipOther(3) must beEmpty
    }

    "return the list if the skip number is 0" in {
      val s = Seq("a", "b")
      s.skipOther(0) mustEqual s
    }

    "return every other element in the list if the skip number is 1" in {
      Seq("a", "b", "c", "d").skipOther(1) mustEqual Seq("b", "d")
    }

    "return every other element in the list if the skip number is greater than 1" in {
      Seq("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k").skipOther(4) mustEqual Seq("e", "j")
    }
  }
}
