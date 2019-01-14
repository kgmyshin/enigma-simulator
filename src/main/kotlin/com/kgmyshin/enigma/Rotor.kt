package com.kgmyshin.enigma

sealed class Rotor(
    private val writingTable: CharArray
) : Converter {

    class RotorI : Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray())
    class RotorII : Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray())
    class RotorIII : Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray())

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
        val input = LETTERS[(initialPosition + tickCount + offset) % LETTERS.size]
        val sendToNext = writingTable[LETTERS.indexOf(input)]
        val nextOffset =
            (sendToNext.toInt() - (initialPosition + tickCount) - 'A'.toInt() + LETTERS.size) % LETTERS.size
        val returnByNextOffset = when (next) {
            is Rotor -> {
                next.convert(nextOffset)
            }
            is Reflector -> {
                next.convert(nextOffset)
            }
            else -> throw RuntimeException("wtf")
        }
        val returnByNext = LETTERS[(initialPosition + tickCount + returnByNextOffset) % LETTERS.size]
        val outOffset =
            (writingTable.indexOf(returnByNext) - (initialPosition + tickCount) + LETTERS.size) % LETTERS.size
        return outOffset
    }
}