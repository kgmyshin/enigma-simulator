package com.kgmyshin.enigma.reflector

import com.kgmyshin.enigma.Alphabet
import com.kgmyshin.enigma.Converter

sealed class Reflector(
  private val wiringTable: WiringTable
) : Converter {

  object ReflectorB : Reflector(
    WiringTable(
      mapOf(
        'A' to 'Y',
        'B' to 'R',
        'C' to 'U',
        'D' to 'H',
        'E' to 'Q',
        'F' to 'S',
        'G' to 'L',
        'I' to 'P',
        'J' to 'X',
        'K' to 'N',
        'M' to 'O',
        'T' to 'Z',
        'V' to 'W'
      )
    )
  )

  override fun tick() {}

  override fun convert(offset: Int): Int {
    val input = Alphabet(offset)
    return Alphabet(wiringTable.pair(input.char)).pos()
  }
}