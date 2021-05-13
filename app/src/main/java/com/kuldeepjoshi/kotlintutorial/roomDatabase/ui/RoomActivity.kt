package com.kuldeepjoshi.kotlintutorial.roomDatabase.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuldeepjoshi.kotlintutorial.MyApplication
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.databinding.ActivityRoomBinding
import com.kuldeepjoshi.kotlintutorial.roomDatabase.WordListAdapter
import com.kuldeepjoshi.kotlintutorial.roomDatabase.model.Word
import com.kuldeepjoshi.kotlintutorial.roomDatabase.viewModel.WordViewModel
import com.kuldeepjoshi.kotlintutorial.roomDatabase.viewModel.WordViewModelFactory

class RoomActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomBinding

    private val newWordActivityRequestCode = 1

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()

    }

    private fun initialization() {

        val adapter = WordListAdapter()

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, { words ->

            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        })

        binding.fab.setOnClickListener {
            Intent(this, NewWordActivity::class.java).also {
                startActivityForResult(it, newWordActivityRequestCode)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == RESULT_OK) {

            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }

        } else {

            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()

        }

    }
}