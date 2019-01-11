package com.kgmyshin.enigma

fun main(args: Array<String>) {
    val enigma1 = Enigma(
        Rotor.RotorI(),
        Rotor.RotorII(),
        Rotor.RotorIII(),
        Reflector.ReflectorB()
    )
    val enigma2 = Enigma(
        Rotor.RotorI(),
        Rotor.RotorII(),
        Rotor.RotorIII(),
        Reflector.ReflectorB()
    )
    println(enigma1.convert("AAAA"))
    println(enigma2.convert("FTZM"))
}