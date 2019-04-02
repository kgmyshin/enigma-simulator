package com.kgmyshin.enigma

import com.kgmyshin.enigma.plugboard.Plugboard
import com.kgmyshin.enigma.reflector.Reflector
import com.kgmyshin.enigma.rotor.Rotor

fun main(args: Array<String>) {
  val plugboard1 = Plugboard(
    com.kgmyshin.enigma.plugboard.WiringTable(
      mapOf(
        'A' to 'C',
        'B' to 'Z'
      )
    )
  )
  val plugboard2 = Plugboard(
    com.kgmyshin.enigma.plugboard.WiringTable(
      mapOf(
        'A' to 'C',
        'B' to 'Z'
      )
    )
  )
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

  val cipher1 = "HELLO".map { enigma1.type(it) }.toCharArray()
  println(cipher1)

  val cipher2 = "MFNAB".map { enigma2.type(it) }.toCharArray()
  println(cipher2)
}