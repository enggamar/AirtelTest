package com.airteltest.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airteltest.data.api.ApiHelper
import com.airteltest.data.repository.SearchRepository
import com.airteltest.ui.viewmodel.SearchViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(SearchRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

