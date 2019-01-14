package com.kgmyshin.enigma

class Plugboard(
    private val writingTable: WritingTable
) {
    private var next: Rotor? = null

    fun connect(next: Rotor) {
        this.next = next
    }

    fun convert(alphabet: Alphabet): Alphabet {
        val next = next ?: throw RuntimeException("not found rotor")
        next.tick()
        val sendToNext = writingTable.frontToBack(alphabet)
        val returnByNext = Alphabet(next.convert(sendToNext.pos()))
        return writingTable.backToFront(returnByNext)
    }

}
