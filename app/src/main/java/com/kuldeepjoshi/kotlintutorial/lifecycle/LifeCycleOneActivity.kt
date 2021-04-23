package com.kuldeepjoshi.kotlintutorial.lifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityLifeCycleOneBinding

class LifeCycleOneActivity : AppCompatActivity() {

    private val TAG = "LifeCycleOneActivity"
    private lateinit var binding: ActivityLifeCycleOneBinding

    private val KEY_COUNT = "key_count"

    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLifeCycleOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(TAG, "onCreate: ")

        if(savedInstanceState != null){
            count = savedInstanceState.getInt(KEY_COUNT)
            binding.txtCount.text = count.toString()
        }

        binding.button.setOnClickListener {
            Intent(this, LifeCycleTwoActivity::class.java).let {
                startActivity(it)
            }
        }

        binding.buttonCount.setOnClickListener {
            count++
            Log.d(TAG, "onCreate: COUNT >> " + count)
            binding.txtCount.text = count.toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e(TAG, "onSaveInstanceState: " )

        outState.putInt(KEY_COUNT, count)

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