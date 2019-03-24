package com.kgmyshin.enigma

class WiringTable(
  charArray: CharArray
) {

  companion object {
    val NORMAL = WiringTable("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray())
  }

  val alphabets = charArray.map { Alphabet(it) }

  init {
    if (
      !charArray.all { Alphabet.ALL.contains(it) } ||
      charArray.size != Alphabet.ALL.size
    ) throw RuntimeException("invalid writing table")
  }

  fun frontToBack(alphabet: Alphabet): Alphabet =
    alphabets[NORMAL.alphabets.indexOf(alphabet)]

  fun backToFront(alphabet: Alphabet): Alphabet =
    NORMAL.alphabets[alphabets.indexOf(alphabet)]
}
