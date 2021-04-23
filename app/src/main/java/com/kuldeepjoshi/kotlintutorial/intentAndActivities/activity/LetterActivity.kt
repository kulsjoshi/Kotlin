package com.kuldeepjoshi.kotlintutorial.intentAndActivities.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityLetterBinding
import com.kuldeepjoshi.kotlintutorial.intentAndActivities.adapter.LetterAdapter

class LetterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLetterBinding

    private lateinit var recyclerView: RecyclerView

    var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLetterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)

        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initialization() {

        recyclerView = binding.recyclerView
        recyclerView.adapter = LetterAdapter()
        chooseLayout()

    }

    private fun chooseLayout() {
        if (isLinearLayoutManager)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        else
            binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
            else
                ContextCompat.getDrawable(this, R.drawable.ic_list_layout)

    }
}