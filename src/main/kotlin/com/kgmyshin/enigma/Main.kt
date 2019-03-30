package com.kgmyshin.enigma

import com.kgmyshin.enigma.reflector.Reflector
import com.kgmyshin.enigma.rotor.Rotor
import com.kgmyshin.enigma.rotor.WiringTable

fun main(args: Array<String>) {
  val plugboard1 = Plugboard(WiringTable("ZBCDEFGHIJKLMNOPQRSTUVWXYA".toCharArray()))
  val plugboard2 = Plugboard(WiringTable("ZBCDEFGHIJKLMNOPQRSTUVWXYA".toCharArray()))
  val enigma1 = Enigma(
    plugboard1,
    Rotor.RotorI(),
    Rotor.RotorII(),
    Rotor.RotorIII(),
    Reflector.ReflectorB
  )
  val enigma2 = Enigma(
    plugboard2,
    Rotor.RotorI(),
    Rotor.RotorII(),
    Rotor.RotorIII(),
    Reflector.ReflectorB
  )
  println(enigma1.convert("AAAAAA"))
  println(enigma2.convert("TVZOOY"))
}