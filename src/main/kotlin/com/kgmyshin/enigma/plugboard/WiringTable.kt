package com.kgmyshin.enigma.plugboard

class WiringTable(
  private val map: Map<Char, Char>
) {

  companion object {
    private val LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
  }

  init {
    if (
      !map.map { listOf(it.key, it.value) }.flatten().all { LETTERS.contains(it) }
    ) throw RuntimeException("invalid writing table")
  }

  fun convertIfNeeded(char: Char): Char {
    map.entries.forEach {
      if (it.key == char) {
        return it.value
      }
      if (it.value == char) {
        return it.key
      }
    }
    return char
  }
}
