package com.kgmyshin.enigma

import com.kgmyshin.enigma.rotor.Rotor
import com.kgmyshin.enigma.rotor.WiringTable

class Plugboard(
    private val wiringTable: WiringTable
) {
    private var next: Rotor? = null

    fun connect(next: Rotor) {
        this.next = next
    }

    fun convert(alphabet: Alphabet): Alphabet {
        val next = next ?: throw RuntimeException("not found rotor")
        next.tick()
        val sendToNext = wiringTable.frontToBack(alphabet)
        val returnByNext = Alphabet(next.convert(sendToNext.pos()))
        return wiringTable.backToFront(returnByNext)
    }

}
