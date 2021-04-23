package com.kuldeepjoshi.kotlintutorial.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityLifeCycleTwoBinding

class LifeCycleTwoActivity : AppCompatActivity() {

    private val TAG = "LifeCycleTwoActivity"
    private lateinit var binding: ActivityLifeCycleTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLifeCycleTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPreviousActivity.setOnClickListener {
            finish()
        }

        Log.e(TAG, "onCreate: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ")
    }
}