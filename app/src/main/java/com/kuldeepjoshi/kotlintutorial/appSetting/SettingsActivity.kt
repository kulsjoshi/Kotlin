package com.kuldeepjoshi.kotlintutorial.appSetting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showSettingsFragment()
    }

    private fun showSettingsFragment() {
        supportFragmentManager.beginTransaction().replace(
            android.R.id.content, SettingsFragment()
        ).commit()
    }
}