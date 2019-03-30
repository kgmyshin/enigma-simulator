package com.kgmyshin.enigma.reflector

class WiringTable(
  private val map: Map<Char, Char>
) {

  companion object {
    private val LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
  }

  init {
    if (
      !map.map { listOf(it.key, it.value) }.flatten().all { LETTERS.contains(it) } ||
      map.size != (LETTERS.size / 2)
    ) throw RuntimeException("invalid writing table")
  }

  fun pair(char: Char): Char {
    map.entries.forEach {
      if (it.key == char) {
        return it.value
      }
      if (it.value == char) {
        return it.key
      }
    }
    throw IllegalArgumentException("not found")
  }
}
