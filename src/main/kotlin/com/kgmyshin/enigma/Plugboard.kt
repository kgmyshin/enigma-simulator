package com.kgmyshin.enigma

class Plugboard(
    private val writingTable: CharArray
) {

    companion object {
        val LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    }

    private var next: Rotor? = null

    fun connect(next: Rotor) {
        this.next = next
    }

    fun convert(char: Char): Char {
        val next = next ?: throw RuntimeException("not found rotor")
        next.tick()
        val converted = (next.convert(writingTable[LETTERS.indexOf(char)].toInt() - 'A'.toInt()) + 'A'.toInt()).toChar()
        return LETTERS[writingTable.indexOf(converted)]
//        return (next.convert(char.toInt() - 'A'.toInt()) + 'A'.toInt()).toChar()
    }

}
