package com.kgmyshin.enigma

interface Converter {
  fun tick()

  fun convert(offset: Int): Int
}