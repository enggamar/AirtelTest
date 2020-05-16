package com.airteltest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.airteltest.data.repository.SearchRepository
import com.airteltest.utils.Resource
import kotlinx.coroutines.Dispatchers


class SearchViewModel(private val mainRepository: SearchRepository) : ViewModel() {
    lateinit var isVisbileLiveData: MutableLiveData<Boolean>
    lateinit var callApiLiveData: MutableLiveData<String>

    init {
        isVisbileLiveData = MutableLiveData()
        callApiLiveData = MutableLiveData()
    }

    fun getSearchAddresses(queryString: String, city: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getSearchAddresses(queryString, city.toLowerCase())))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun setVisibilty(): LiveData<Boolean> {
        return isVisbileLiveData
    }

    fun callApi(): LiveData<String> {
        return callApiLiveData
    }

    fun onSearchFieldTextChanged(s: CharSequence) {
        if (s.toString().trim().length > 0) {
            callApiLiveData.value = s.toString().trim()
        } else {
            callApiLiveData.value = ""
        }
    }

    fun onCrossSearchClicked() {
        callApiLiveData.value = ""
    }


}