package com.kuldeepjoshi.kotlintutorial.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityRecyclerViewBinding
import com.kuldeepjoshi.kotlintutorial.recyclerView.adapter.ItemAdapter
import com.kuldeepjoshi.kotlintutorial.recyclerView.data.DataSource

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()

    }

    private fun initialization() {

        // Initialize data.
        val dataSet = DataSource().loadAffirmation()

        binding.recyclerView.adapter = ItemAdapter(this, dataSet)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.recyclerView.setHasFixedSize(true)

    }
}