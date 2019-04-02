package com.kgmyshin.enigma.plugboard

import com.kgmyshin.enigma.Alphabet
import com.kgmyshin.enigma.rotor.Rotor

class Plugboard(
  private val wiringTable: WiringTable
) {
  private var next: Rotor? = null

  fun connect(next: Rotor) {
    this.next = next
  }

  fun convert(char: Char): Char {
    val next = next ?: throw RuntimeException("not found rotor")
    next.tick()
    val sendToNext = wiringTable.convertIfNeeded(char).let { Alphabet(it) }
    val returnByNext = Alphabet(next.convert(sendToNext.pos()))
    return wiringTable.convertIfNeeded(returnByNext.char)
  }
}
