/*
 * Copyright (c) Vincent Nalbone 2016
 */

package mock.com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.core.card.ProgramCard
import com.nalbisoft.roborally.domain.game.{Player, PlayerId}

case class PlayerSpy(id: PlayerId, name: String, programCards: Option[Seq[ProgramCard]] = None) extends Player {
  var passedProgramCards: Option[Seq[ProgramCard]] = None

  override def acceptProgramCards(cards: Seq[ProgramCard]) = {
    passedProgramCards = Some(cards)
  }
}
