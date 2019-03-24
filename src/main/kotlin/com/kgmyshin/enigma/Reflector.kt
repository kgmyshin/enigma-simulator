package com.kgmyshin.enigma

sealed class Reflector(
  private val wiringTable: WiringTable
) : Converter {

  object ReflectorB : Reflector(WiringTable("YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray()))

  override fun tick() {}

  override fun convert(pos: Int): Int = wiringTable.frontToBack(Alphabet(pos)).pos()
}