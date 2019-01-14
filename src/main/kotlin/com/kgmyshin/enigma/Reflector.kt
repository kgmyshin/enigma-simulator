package com.kgmyshin.enigma

sealed class Reflector(
    private val writingTable: WritingTable
) : Converter {

    class ReflectorB : Reflector(WritingTable("YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray()))

    fun convert(pos: Int): Int = writingTable.frontToBack(Alphabet(pos)).pos()
}