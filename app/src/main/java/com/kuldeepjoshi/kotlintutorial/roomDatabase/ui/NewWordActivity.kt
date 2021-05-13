package com.kuldeepjoshi.kotlintutorial.roomDatabase.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()

    }

    private fun initialization() {

        binding.buttonSave.setOnClickListener {

            val replyIntent = Intent()

            if (TextUtils.isEmpty(binding.editWord.text)) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = binding.editWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(RESULT_OK, replyIntent)
                finish()
            }

        }

    }

    companion object {
        const val EXTRA_REPLY = "REPLY"
    }
}