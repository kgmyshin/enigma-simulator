package com.kgmyshin.enigma.rotor

import com.kgmyshin.enigma.Alphabet
import com.kgmyshin.enigma.Converter

sealed class Rotor(
  private val wiringTable: WiringTable
) : Converter {

  class RotorI : Rotor(WiringTable("EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray()))
  class RotorII : Rotor(WiringTable("AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray()))
  class RotorIII : Rotor(WiringTable("BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray()))

  private var initialSetting = 0
  private var tickCount = 0
  private var next: Converter? = null

  fun connect(next: Converter) {
    this.next = next
  }

  fun setInitialPosition(initialPosition: Int) {
    this.initialSetting = initialPosition
  }

  override fun tick() {
    val next = next ?: throw RuntimeException("not found next")
    tickCount = (tickCount + 1) % wiringTable.alphabets.size
    if (tickCount == 0) {
      next.tick()
    }
  }

  override fun convert(offset: Int): Int {
    val next = next ?: throw RuntimeException("not found next")
    val offsetForNext = frontToBack(offset)
    val returnByNext = next.convert(offsetForNext)
    return backToFront(returnByNext)
  }

  private fun frontToBack(offset: Int): Int {
    val setting = Alphabet(initialSetting + tickCount)
    val input = setting.plus(offset)
    val cyphering = wiringTable.frontToBack(input)
    return cyphering.diff(setting)
  }

  private fun backToFront(offset: Int): Int {
    val setting = Alphabet(initialSetting + tickCount)
    val input = setting.plus(offset)
    val cyphering = wiringTable.backToFront(input)
    return cyphering.diff(setting)
  }
}