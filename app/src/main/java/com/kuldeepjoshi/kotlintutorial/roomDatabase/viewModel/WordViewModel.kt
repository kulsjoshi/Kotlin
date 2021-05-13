package com.kuldeepjoshi.kotlintutorial.roomDatabase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kuldeepjoshi.kotlintutorial.roomDatabase.model.Word
import com.kuldeepjoshi.kotlintutorial.roomDatabase.repository.WordRepository
import kotlinx.coroutines.launch

/**
 * ======================
 * What is a ViewModel?
 * ======================
 * The ViewModel's role is to provide data to the UI and survive configuration changes. A ViewModel
 * acts as a communication center between the Repository and the UI. You can also use a ViewModel
 * to share data between fragments. The ViewModel is part of the lifecycle library.
 *
 * ======================
 * Why use a ViewModel?
 * ======================
 * A ViewModel holds your app's UI data in a lifecycle-conscious way that survives configuration
 * changes. Separating your app's UI data from your Activity and Fragment classes lets you better
 * follow the single responsibility principle: Your activities and fragments are responsible for
 * drawing data to the screen, while your ViewModel can take care of holding and processing all the
 * data needed for the UI.
 *
 * ======================
 * LiveData and ViewModel
 * ======================
 * LiveData is an observable data holder - you can get notified every time the data changes.
 * Unlike Flow, LiveData is lifecycle aware, meaning that it will respect the lifecycle of other
 * components like Activity or Fragment. LiveData automatically stops or resumes observation
 * depending on the lifecycle of the component that listens for changes. This makes LiveData the
 * perfect component to be used for for changeable data that the UI will use or display.
 *
 * The ViewModel will transform the data from the Repository, from Flow to LiveData and exposes the
 * list of words as LiveData to the UI. This ensures that every time the data changes in the
 * database, your UI is automatically updated.
 *
 * ======================
 * viewModelScope
 * ======================
 * In Kotlin, all coroutines run inside a CoroutineScope. A scope controls the lifetime of
 * coroutines through its job. When you cancel the job of a scope, it cancels all coroutines
 * started in that scope.
 *
 * The AndroidX lifecycle-viewmodel-ktx library adds a viewModelScope as an extension function of
 * the ViewModel class, enabling you to work with scopes.
 *
 */
class WordViewModel(private val wordRepository: WordRepository):ViewModel() {

    /**
     * Using LiveData and caching what allWords returns has several benefits:
     *    - We can put an observer on the data (instead of polling for changes) and only update the
     *   the UI when the data actually changes.
     *    - Repository is completely separated from the UI through the ViewModel.
     */
    val allWords: LiveData<List<Word>> = wordRepository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }

}