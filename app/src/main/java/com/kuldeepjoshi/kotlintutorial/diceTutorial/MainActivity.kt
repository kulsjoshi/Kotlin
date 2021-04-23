package com.kuldeepjoshi.kotlintutorial.diceTutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val myFirstDice = Dice(1000)
        val dicedRoll = myFirstDice.roll()
        println("Your ${myFirstDice.numSides} sided dice rolled a ${dicedRoll}")
//        println("My First Dice is ${myFirstDice.sides}")

//        myFirstDice.sides = 100
//        println("Your ${myFirstDice.sides} sided dice rolled a ${myFirstDice.roll()}")

        val mySecondDice = Dice(18)
        println("Your ${mySecondDice.numSides} sided dice rolled a ${mySecondDice.roll()}")

    }

    private fun DiceFunction() {
        val diceRange = 1..6
        val randomNumber = diceRange.random()
        println("Random dice is $randomNumber")
    }
}