package com.kuldeepjoshi.kotlintutorial.diceTutorial

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.R

class DiceRollWithButtonActivity : AppCompatActivity() {

    private val luckyNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roll_with_button)
        initialization()
    }

    private fun initialization() {

        val buttonRoll = findViewById<Button>(R.id.btnRollDice)
        val txtDice = findViewById<TextView>(R.id.txtDiceRolled)

        buttonRoll.setOnClickListener {
            showToast()
            val rollMyDice = Dice(6)
            val rollResult = rollMyDice.roll()
            txtDice.text = rollResult.toString()

            when (rollResult) {

                luckyNumber -> println("Result >> You won!")
                1 -> println("So sorry! You rolled a 1. Try again!")
                2 -> println("Sadly, you rolled a 2. Try again!")
                3 -> println("Unfortunately, you rolled a 3. Try again!")
                4 -> println("No luck! You rolled a 4. Try again!")
                5 -> println("Don't cry! You rolled a 5. Try again!")
                6 -> println("Apologies! you rolled a 6. Try again!")


            }

        }
    }

    private fun showToast() {
        Toast.makeText(this, "Dice Rolled", Toast.LENGTH_SHORT).show()
    }
}