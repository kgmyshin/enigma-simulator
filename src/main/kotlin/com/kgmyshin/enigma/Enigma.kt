package com.kgmyshin.enigma

import com.kgmyshin.enigma.plugboard.Plugboard
import com.kgmyshin.enigma.reflector.Reflector
import com.kgmyshin.enigma.rotor.Rotor

class Enigma(
  private val plugboard: Plugboard,
  rightRotor: Rotor,
  middleRotor: Rotor,
  leftRotor: Rotor,
  reflector: Reflector
) {
  init {
    plugboard.connect(rightRotor)
    rightRotor.connect(middleRotor)
    middleRotor.connect(leftRotor)
    leftRotor.connect(reflector)
  }

  fun type(char: Char): Char {
    return plugboard.convert(char)
  }
}