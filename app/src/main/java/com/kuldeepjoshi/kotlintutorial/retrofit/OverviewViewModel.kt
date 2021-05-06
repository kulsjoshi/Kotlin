package com.kuldeepjoshi.kotlintutorial.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuldeepjoshi.kotlintutorial.retrofit.network.MarsApi
import com.kuldeepjoshi.kotlintutorial.retrofit.network.MarsPhotos
import kotlinx.coroutines.launch

enum class MarsApiStatus{ LOADING, ERROR, DONE}

//====================================
//OverviewViewModel:
//====================================

//This is the corresponding view model for the OverviewFragment.
//This class contains a MutableLiveData property named _status along with its backing property.
//Updating the value of this property, updates the placeholder text displayed on the screen.

/**
 * The [ViewModel] that is attached to the [MarsOverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    //This internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<MarsApiStatus>()

    private val _photos = MutableLiveData<List<MarsPhotos>>()

    //This external immutable LiveData for the request status
    val status: LiveData<MarsApiStatus> = _status

    val photos: LiveData<List<MarsPhotos>> = _photos

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhotos] [List] [LiveData].
     */
    private fun getMarsPhotos() {

        _status.value = MarsApiStatus.LOADING

        viewModelScope.launch {

            try {

                _photos.value = MarsApi.retrofitService.getMarsPhotos()
                _status.value = MarsApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }

}