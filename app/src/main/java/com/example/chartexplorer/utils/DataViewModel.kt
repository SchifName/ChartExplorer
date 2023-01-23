package com.example.chartexplorer.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chartexplorer.network.AnimalsInfo
import com.example.chartexplorer.network.AnimalsInfoFromInternet
import com.example.chartexplorer.network.AnimalsNet
import com.example.chartexplorer.network.AnimalsNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class DataViewModel : ViewModel() {

    private val _animal = MutableLiveData<AnimalsInfoFromInternet>()

    val animal: LiveData<AnimalsInfoFromInternet>
        get() = _animal

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        dataAnimalsAcquisition()
    }

    /**
     * Function for acquiring pie chart data via API
     */
    fun dataAnimalsAcquisition() = CoroutineScope(Dispatchers.Main).launch {
        try {
            _animal.value = AnimalsNet.retrofitService.getAnimalsInfo()

            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            _eventNetworkError.value = true
        }
    }



}