package com.kgmyshin.enigma

sealed class Reflector(
    private val writingTable: CharArray
) : Converter {

    class ReflectorB : Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray())

    fun convert(pos: Int): Int = writingTable[pos].toInt() - 'A'.toInt()
}