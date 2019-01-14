package com.kgmyshin.enigma

sealed class Rotor(
    private val writingTable: WritingTable
) : Converter {

    class RotorI : Rotor(WritingTable("EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray()))
    class RotorII : Rotor(WritingTable("AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray()))
    class RotorIII : Rotor(WritingTable("BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray()))

    companion object {
        val LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    }

    private var initialPosition = 0
    private var tickCount = 0
    private var next: Converter? = null

    fun connect(next: Converter) {
        this.next = next
    }

    fun setInitialPosition(initialPosition: Int) {
        this.initialPosition = initialPosition
    }

    fun tick() {
        val next = next ?: throw RuntimeException("not found next rotor")
        if (tickCount == (LETTERS.size - 1) && next is Rotor) {
            next.tick()
        }
        tickCount = (tickCount + 1) % LETTERS.size
    }

    fun convert(offset: Int): Int {
        val next = next ?: throw RuntimeException("not found next rotor")
        val currentStart = Alphabet(initialPosition + tickCount)
        val input = Alphabet(initialPosition + tickCount + offset)
        val sendToNext = writingTable.frontToBack(input)
        val nextOffset = sendToNext.diff(currentStart)
        val returnByNext = when (next) {
            is Rotor -> {
                next.convert(nextOffset)
            }
            is Reflector -> {
                next.convert(nextOffset)
            }
            else -> throw RuntimeException("wtf")
        }.let { Alphabet(initialPosition + tickCount + it) }
        return writingTable.backToFront(returnByNext).diff(currentStart)
    }
}