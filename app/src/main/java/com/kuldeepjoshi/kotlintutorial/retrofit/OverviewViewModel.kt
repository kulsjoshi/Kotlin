package com.kuldeepjoshi.kotlintutorial.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuldeepjoshi.kotlintutorial.retrofit.network.MarsApi
import kotlinx.coroutines.launch

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
    private val _status = MutableLiveData<String>()

    //This external immutable LiveData for the request status
    val status: LiveData<String> = _status

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos(){
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getMarsPhotos()
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

}