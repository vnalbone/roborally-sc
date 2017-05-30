/*
 * Copyright (c) Vincent Nalbone 2017
 */

package com.nalbisoft.roborally.domain.game

import com.nalbisoft.roborally.domain.RegisterNumber
import com.nalbisoft.roborally.domain.core.card.ProgramCard

case class PlayerCard(playerId: PlayerId, registerNum: RegisterNumber, card: ProgramCard)
