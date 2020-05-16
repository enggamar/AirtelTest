package com.airteltest.data.repository

import com.airteltest.data.api.ApiHelper

class SearchRepository(private val apiHelper: ApiHelper) {

    suspend fun getSearchAddresses(queryString: String, city: String) = apiHelper.getSearchAddresses(queryString,city)
}