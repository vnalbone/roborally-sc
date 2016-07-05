package mock.com.nalbisoft.roborally.domain

import com.nalbisoft.roborally.domain.core.card.{CardDeck, ProgramCard}

class MockCardDeck(cards: Seq[ProgramCard]) extends CardDeck {
  val iter = cards.iterator

  override def next(): ProgramCard = {
    iter.next()
  }
}
