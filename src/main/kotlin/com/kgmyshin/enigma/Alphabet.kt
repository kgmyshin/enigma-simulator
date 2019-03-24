package com.kgmyshin.enigma

data class Alphabet(val char: Char) {

  constructor(pos: Int) : this(ALL[adjust(pos)])

  init {
    if (!ALL.contains(char))
      throw IllegalArgumentException("not uppercase alphabet")
  }

  companion object {
    val ALL =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    private val COUNT_OF_ALL = ALL.size

    private fun adjust(pos: Int): Int {
      var adjusted = pos
      while (adjusted !in 0..(COUNT_OF_ALL - 1)) {
        if (adjusted < 0) {
          adjusted += COUNT_OF_ALL
        } else {
          adjusted -= COUNT_OF_ALL
        }
      }
      return adjusted
    }
  }

  fun pos(): Int = ALL.indexOf(char)

  fun diff(alphabet: Alphabet): Int = pos() - alphabet.pos()

  fun plus(offset: Int): Alphabet = Alphabet(pos() + offset)
}
