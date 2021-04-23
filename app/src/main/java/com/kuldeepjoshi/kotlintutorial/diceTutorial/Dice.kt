package com.kuldeepjoshi.kotlintutorial.diceTutorial

class Dice(val numSides: Int) {
//    var sides = 6

    fun roll(): Int {
        return (1..numSides).random()
    }

}