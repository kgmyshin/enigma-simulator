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

    private var initialOffset = 0
    private var offset = 0
    private var next: Converter? = null

    fun connect(next: Converter) {
        this.next = next
    }

    fun tick() {
        val next = next ?: throw RuntimeException("not found next rotor")
        if (offset == (LETTERS.size - 1) && next is Rotor) {
            next.tick()
        }
        offset = (offset + 1) % LETTERS.size
    }

    fun convert(pos: Int): Int {
        val next = next ?: throw RuntimeException("not found next rotor")
        val nextPos =
            (writingTable[(initialOffset + offset + pos) % LETTERS.size].toInt() - (initialOffset + offset) - 'A'.toInt() + LETTERS.size) % LETTERS.size
        val convertedPos = when (next) {
            is Rotor -> {
                next.convert(nextPos)
            }
            is Reflector -> {
                next.convert(nextPos)
            }
            else -> throw RuntimeException("wtf")
        }
        return (writingTable.indexOf(LETTERS[(initialOffset + offset + convertedPos) % LETTERS.size]) - (initialOffset + offset) + LETTERS.size) % LETTERS.size
    }
}