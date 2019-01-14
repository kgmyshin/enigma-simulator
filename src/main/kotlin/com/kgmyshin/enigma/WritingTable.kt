package com.kgmyshin.enigma

class WritingTable(
    charArray: CharArray
) {

    companion object {
        val NORMAL = WritingTable("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray())
    }

    val alphabets = charArray.map { Alphabet(it) }

    init {
        if (
            alphabets.size != alphabets.distinct().size ||
            alphabets.size != Alphabet.COUNT_OF_ALL
        ) throw RuntimeException("invalid writing table")
    }


    fun frontToBack(alphabet: Alphabet): Alphabet = alphabets[NORMAL.alphabets.indexOf(alphabet)]
    fun backToFront(alphabet: Alphabet): Alphabet = NORMAL.alphabets[alphabets.indexOf(alphabet)]
}
