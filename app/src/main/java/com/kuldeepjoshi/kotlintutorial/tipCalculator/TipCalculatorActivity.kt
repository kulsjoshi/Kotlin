package com.kuldeepjoshi.kotlintutorial.tipCalculator

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityTipCalculatorBinding
import java.text.NumberFormat
import kotlin.math.ceil

class TipCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTipCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()

    }

    private fun initialization() {

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.costOfService.setOnKeyListener { v, keyCode, _ ->
            handleKeyEvent(v, keyCode)
        }
    }

    private fun calculateTip() {

        val stringCost = binding.costOfService.text.toString()
        val costInDec = stringCost.toDoubleOrNull()

        if (costInDec == null || costInDec == 0.0) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {

            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15

        }

        var tip = tipPercentage * costInDec

        if (binding.roundUpSwitch.isChecked)
            tip = ceil(tip)

        displayTip(tip)

    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }


}