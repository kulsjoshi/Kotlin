package com.kuldeepjoshi.kotlintutorial.roomDatabase.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kuldeepjoshi.kotlintutorial.roomDatabase.repository.WordRepository

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown viewModel class")

    }
}