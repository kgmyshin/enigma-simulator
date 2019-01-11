package com.kgmyshin.enigma

class Enigma(
    private val rightRotor: Rotor,
    middleRotor: Rotor,
    leftRotor: Rotor,
    reflector: Reflector
) {
    init {
        rightRotor.connect(middleRotor)
        middleRotor.connect(leftRotor)
        leftRotor.connect(reflector)
    }

    fun convert(str: String): String {
        val upperCasedCharArray = str.toUpperCase().toCharArray()
        val buff = StringBuffer()
        upperCasedCharArray.forEach { char ->
            rightRotor.tick()
            buff.append((rightRotor.convert(char.toInt() - 'A'.toInt()) + 'A'.toInt()).toChar())
        }
        return buff.toString()
    }
}