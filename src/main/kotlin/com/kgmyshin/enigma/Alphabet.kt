package com.kgmyshin.enigma

data class Alphabet(val char: Char) {

    constructor(pos: Int) : this(LETTERS[pos % COUNT_OF_ALL])

    companion object {
        private val LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        val COUNT_OF_ALL = LETTERS.size
    }

    fun pos(): Int = LETTERS.indexOf(char)

    fun diff(alphabet: Alphabet): Int = (pos() - alphabet.pos() + COUNT_OF_ALL) % COUNT_OF_ALL

    operator fun plus(alphabet: Alphabet) = Alphabet((pos() + alphabet.pos()) % COUNT_OF_ALL)

    operator fun minus(alphabet: Alphabet) = Alphabet((pos() - alphabet.pos() + COUNT_OF_ALL) % COUNT_OF_ALL)
}