package com.kuldeepjoshi.kotlintutorial.intentAndActivities.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityLetterDetailsBinding
import com.kuldeepjoshi.kotlintutorial.intentAndActivities.adapter.LetterDetailAdapter

class LetterDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLetterDetailsBinding

    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLetterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()

    }

    private fun initialization() {

        val letterId = intent.extras?.getString(LETTER).toString()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = LetterDetailAdapter(letterId, this)

    }
}