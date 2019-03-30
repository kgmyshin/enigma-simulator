package com.kgmyshin.enigma

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

    fun convert(str: String): String {
        val upperCasedCharArray = str.toUpperCase().toCharArray()
        val buff = StringBuffer()
        upperCasedCharArray.forEach { char ->
            buff.append(plugboard.convert(Alphabet(char)).char)
        }
        return buff.toString()
    }
}