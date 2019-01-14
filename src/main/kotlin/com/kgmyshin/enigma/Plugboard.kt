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
        val sendToNext = writingTable[LETTERS.indexOf(char)].toInt() - 'A'.toInt()
        val returnByNext = ((next.convert(sendToNext) + 'A'.toInt()).toChar())
        val out = LETTERS[writingTable.indexOf(returnByNext)]
        return out
    }

}
